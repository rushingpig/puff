package net.blissmall.puff.web.controller.rest.user;

import com.google.common.collect.Maps;
import net.blissmall.puff.api.sms.SmsService;
import net.blissmall.puff.api.user.UserService;
import net.blissmall.puff.common.utils.CookieUtils;
import net.blissmall.puff.common.utils.CryptoUtils;
import net.blissmall.puff.common.utils.EncodeUtils;
import net.blissmall.puff.common.utils.StringUtils;
import net.blissmall.puff.core.validation.group.user.*;
import net.blissmall.puff.domain.user.AppUserAuths;
import net.blissmall.puff.service.constant.ErrorStatus;
import net.blissmall.puff.service.constant.PuffNamedConstant;
import net.blissmall.puff.vo.response.BaseResponseVo;
import net.blissmall.puff.vo.sms.SmsVo;
import net.blissmall.puff.vo.user.LoginVo;
import net.blissmall.puff.vo.user.RegistryLoginVo;
import net.blissmall.puff.vo.user.RegistryVo;
import net.blissmall.puff.web.controller.BaseRestController;
import net.blissmall.puff.web.j2ee.ValidateCodeServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static net.blissmall.puff.service.constant.PuffNamedConstant.USER_SESSION_KEY;

/**
 * 用户相关的controller
 * @Author : zhuzhenglin
 * @Date : 16/8/7 00:11
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@RestController
@RequestMapping("/user")
public class UserRestController extends BaseRestController {

    @Resource
    private SmsService smsService;
    @Resource
    private UserService userService;

    @Value("${puff.cookie.maxAge}")
    private int maxAge;


    /**
     * 组装参数异常时返回的rest err信息
     * @param field
     * @param value
     * @param info
     * @return
     */
    private BaseResponseVo assembleRestParamErr(String field,String value,String info){
        Map<String,String> errMap = Maps.newHashMap();
        errMap.put("field",field);
        errMap.put("value",value);
        errMap.put("info", puffLocaleMessageSourceHolder.getMessage(info));
        return restRes(ErrorStatus.INVALID_PARAMS.getCode(),ErrorStatus.INVALID_PARAMS.getMsg(),errMap);
    }

    private SmsVo parseLoginVo2SmsVo(RegistryLoginVo registryLoginVo, SmsVo.SmsType smsType){
        SmsVo smsVo = new SmsVo();
        smsVo.setCode(registryLoginVo.getSmsValidateCode());
        smsVo.setSmsType(smsType);
        smsVo.setPhoneNum(registryLoginVo.getUsername());
        return smsVo;
    }

    @GetMapping("exist")
    public BaseResponseVo restExist(@Validated(ValidateUsernameGroup.class) RegistryLoginVo registryLoginVo, BindingResult bindingResult, HttpServletResponse response){

        Map<String,Boolean> result = Maps.newHashMap();
        boolean exist = userService.userExist(registryLoginVo.getUsername());
        result.put("exist",exist);
        return restRes(result);
    }

    @PostMapping("registry")
    public BaseResponseVo restRegistry(@Validated({RegistrySequeceGroup.class}) @RequestBody RegistryVo registryVo, BindingResult bindingResult, HttpServletResponse response, HttpSession session){
        String username = registryVo.getUsername();
        if(userService.userExist(username)){
            return bad(ErrorStatus.USERNAME_ALREADY_EXIST);
        }
        String password = registryVo.getPassword();
        String confirmPassword = registryVo.getConfirmPassword();
        if(!StringUtils.equals(password,confirmPassword)){
            return assembleRestParamErr("confirmPassword","********","Consistent.RegistryVo.confirmPassword");
        }
        Object sessionImageValidateCode = session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
        if(sessionImageValidateCode == null || !StringUtils.equalsIgnoreCase(registryVo.getImageValidateCode(),sessionImageValidateCode.toString())){
            return assembleRestParamErr("imageValidateCode",registryVo.getImageValidateCode(),"Consistent.RegistryVo.imageValidateCode");
        }
        smsService.validateSmsCode(parseLoginVo2SmsVo(registryVo, SmsVo.SmsType.REGISTRY));
        if(userService.addUser(registryVo)){
            return ok();
        }else{
            return fail();
        }
    }

    @PostMapping("login")
    public BaseResponseVo login(@Validated({LoginGroup.class}) @RequestBody LoginVo loginVo,BindingResult bindingResult, HttpServletResponse response,HttpSession session){
        if(!userService.userExist(loginVo.getUsername())){
            return bad(ErrorStatus.USER_NOT_EXIST);
        }
        AppUserAuths appUserAuths = new AppUserAuths();
        appUserAuths.setAuthId(loginVo.getUsername());
        // 写入cookie,实现自动登录
        if(userService.validateLogin(loginVo)){
            loginThenWriteCookie(loginVo,appUserAuths,session,response);
            return ok();
        }else{
            return bad(ErrorStatus.ERROR_USERNAME_PASSWORD);
        }
    }

    @PostMapping("quickLogin")
    public BaseResponseVo quickLogin(@Validated({QuickLoginGroup.class}) @RequestBody LoginVo loginVo, BindingResult bindingResult, HttpServletResponse response,HttpSession session){
        Map<String,Boolean> userInfoMap = Maps.newHashMap();
        if(smsService.validateSmsCode(parseLoginVo2SmsVo(loginVo,SmsVo.SmsType.QUIDK_LOGIN))){

            AppUserAuths appUserAuths = new AppUserAuths();
            appUserAuths.setAuthId(loginVo.getUsername());
            // 把用户信息加入到客户端cookie
            if(loginVo.isRememberMe()){
                loginThenWriteCookie(loginVo,appUserAuths,session,response);
            }
            // 返回客户端是否设置过密码
            userInfoMap.put("setPassword",StringUtils.isNotBlank(userService.getUser(appUserAuths).getAuthToken()));
            return restRes(userInfoMap);
        }else {
            return bad(ErrorStatus.ERROR_SMS_VALIDATE_CODE);
        }
    }

    @PutMapping("reset")
    public BaseResponseVo resetPwd(@Validated(ResetPwdGroup.class) @RequestBody RegistryVo registryVo,BindingResult bindingResult,HttpServletResponse response,HttpSession session){
        String password = registryVo.getPassword();
        String confirmPassword = registryVo.getConfirmPassword();
        if(!StringUtils.equals(password,confirmPassword)){
            return assembleRestParamErr("confirmPassword","********","Consistent.RegistryVo.confirmPassword");
        }
        if(session.getAttribute(USER_SESSION_KEY) == null){
            return bad(ErrorStatus.RESET_PASSOWRD_FORBIDDEN);
        }
        userService.changePassword(registryVo);
        return ok();
    }

    /**
     * 登录成功后将cookie写入客户端
     * @param loginVo
     * @param appUserAuths
     * @param session
     * @param response
     */
    private void loginThenWriteCookie(LoginVo loginVo,AppUserAuths appUserAuths,HttpSession session,HttpServletResponse response){
        session.setAttribute(USER_SESSION_KEY,appUserAuths);
        String usernmae = loginVo.getUsername();
        String password = loginVo.getPassword();
        if(loginVo.isRememberMe()){
            long validTimePoint = System.currentTimeMillis() + maxAge * 1000;
            String md5UsernamePassword = CryptoUtils.MD5(usernmae + CryptoUtils.MD5(password) + PuffNamedConstant.COOKIE_ENCODE_SALT);
            String cookieValue = EncodeUtils.encodeBase64(usernmae + PuffNamedConstant.COOKIE_ENCODE_SEP + validTimePoint + PuffNamedConstant.COOKIE_ENCODE_SEP + md5UsernamePassword);
            CookieUtils.setCookie(response, PuffNamedConstant.PUFF_COOKIE_NAME, cookieValue,maxAge);
        }
    }

}
