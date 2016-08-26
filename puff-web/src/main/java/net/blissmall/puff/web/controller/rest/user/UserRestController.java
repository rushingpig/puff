package net.blissmall.puff.web.controller.rest.user;

import com.google.common.collect.Maps;
import net.blissmall.puff.api.sms.SmsService;
import net.blissmall.puff.api.user.UserService;
import net.blissmall.puff.common.utils.*;
import net.blissmall.puff.core.mapper.MyMapper;
import net.blissmall.puff.core.validation.group.user.*;
import net.blissmall.puff.domain.user.AppUserAuths;
import net.blissmall.puff.domain.user.AppUserDeliveryAddress;
import net.blissmall.puff.domain.user.AppUserFavorites;
import net.blissmall.puff.domain.user.AppUserProfiles;
import net.blissmall.puff.service.constant.ErrorStatus;
import net.blissmall.puff.service.constant.PuffNamedConstant;
import net.blissmall.puff.vo.http.BaseResponseVo;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
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
        return bad(ErrorStatus.INVALID_PARAMS,errMap);
    }
    /**
     * 登录成功后将cookie写入客户端并将
     * @param loginVo
     * @param appUserAuths
     * @param session
     * @param response
     */
    private void writeSessionAndCookie(LoginVo loginVo,AppUserAuths appUserAuths,HttpSession session,HttpServletResponse response){
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

    /**
     * 将登录的vo类转换成发送短信需要的vo
     * @param registryLoginVo
     * @param smsType
     * @return
     */
    private SmsVo parseLoginVo2SmsVo(RegistryLoginVo registryLoginVo, SmsVo.SmsType smsType){
        SmsVo smsVo = new SmsVo();
        smsVo.setCode(registryLoginVo.getSmsValidateCode());
        smsVo.setSmsType(smsType);
        smsVo.setPhoneNum(registryLoginVo.getUsername());
        return smsVo;
    }

    /**
     * 获取用户是否存在
     * @param registryLoginVo
     * @param bindingResult
     * @param response
     * @return
     */
    @GetMapping("exist")
    public BaseResponseVo restExist(@Validated(ValidateUsernameGroup.class) RegistryLoginVo registryLoginVo, BindingResult bindingResult, HttpServletResponse response){

        Map<String,Boolean> result = Maps.newHashMap();
        boolean exist = userService.userExist(registryLoginVo.getUsername());
        result.put("exist",exist);
        return data(result);
    }

    /**
     * 用户注册
     * @param registryVo
     * @param bindingResult
     * @param response
     * @param session
     * @return
     */
    @PostMapping("registry")
    public BaseResponseVo restRegistry(@Validated({RegistrySequeceGroup.class}) @RequestBody RegistryVo registryVo, BindingResult bindingResult, HttpServletResponse response, HttpSession session){
        String username = registryVo.getUsername();
        // 校验用户名是否已经存在
        if(userService.userExist(username)){
            return bad(ErrorStatus.USERNAME_ALREADY_EXIST);
        }
        String password = registryVo.getPassword();
        String confirmPassword = registryVo.getConfirmPassword();
        if(!StringUtils.equals(password,confirmPassword)){
            return assembleRestParamErr("confirmPassword","********","Consistent.RegistryVo.confirmPassword");
        }
        // 校验图片验证码
        Object sessionImageValidateCode = session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
        if(sessionImageValidateCode == null || !StringUtils.equalsIgnoreCase(registryVo.getImageValidateCode(),sessionImageValidateCode.toString())){
            return assembleRestParamErr("imageValidateCode",registryVo.getImageValidateCode(),"Consistent.RegistryVo.imageValidateCode");
        }else{
            session.removeAttribute(ValidateCodeServlet.VALIDATE_CODE);
        }
        // 校验短信验证码
        smsService.validateSmsCode(parseLoginVo2SmsVo(registryVo, SmsVo.SmsType.REGISTRY));
        // 添加用户账号、用户基本信息、用户登录日志
        if(StringUtils.isNotBlank(userService.addUserAndInfo(registryVo))){
            return ok();
        }else{
            return fail();
        }
    }

    /**
     * 用户登录
     * @param loginVo
     * @param bindingResult
     * @param response
     * @param session
     * @return
     */
    @PostMapping("login")
    public BaseResponseVo login(@Validated({LoginGroup.class}) @RequestBody LoginVo loginVo,BindingResult bindingResult, HttpServletResponse response,HttpSession session,HttpServletRequest request){
        if(!userService.userExist(loginVo.getUsername())){
            return bad(ErrorStatus.USER_NOT_EXIST);
        }
        if(userService.overLogin(loginVo)){
            Object sessionImageValidateCode = session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
            if(sessionImageValidateCode == null || !StringUtils.equalsIgnoreCase(loginVo.getImageValidateCode(),sessionImageValidateCode.toString())){
                return assembleRestParamErr("imageValidateCode",loginVo.getImageValidateCode(),"Consistent.RegistryVo.imageValidateCode");
            }
        }
        AppUserAuths appUserAuths = new AppUserAuths();
        appUserAuths.setAuthId(loginVo.getUsername());
//        loginVo.setIp(ServletUtils.getRequestIP(request));
        loginVo.setIp("120.234.2.50");
        String uuid = userService.validateLogin(loginVo);
        if(StringUtils.isNotBlank(uuid)){
            // 写入cookie,实现自动登录
            writeSessionAndCookie(loginVo,appUserAuths,session,response);
            return ok();
        }else{
            userService.recordLoginFailure(loginVo);
            Map<String,Boolean> dataMap = Maps.newHashMap();
            dataMap.put("overLogin",userService.overLogin(loginVo));
            return bad(ErrorStatus.ERROR_USERNAME_PASSWORD,dataMap,null);
        }
    }

    /**
     * 用户快速登录
     * @param loginVo
     * @param bindingResult
     * @param response
     * @param session
     * @return
     */
    @PostMapping("quickLogin")
    public BaseResponseVo quickLogin(@Validated({QuickLoginGroup.class}) @RequestBody LoginVo loginVo, BindingResult bindingResult, HttpServletResponse response,HttpSession session){
        Map<String,Boolean> userInfoMap = Maps.newHashMap();
        if(smsService.validateSmsCode(parseLoginVo2SmsVo(loginVo,SmsVo.SmsType.QUIDK_LOGIN))){

            AppUserAuths appUserAuths = new AppUserAuths();
            appUserAuths.setAuthId(loginVo.getUsername());
            // 用户不存在,就自动完成注册
            appUserAuths = userService.getUser(appUserAuths);
            if( appUserAuths == null){
                String uuid = userService.addUserAndInfo(loginVo);
                if(StringUtils.isNotBlank(uuid)){
                    appUserAuths.setUuid(uuid);
                }else{
                    return fail(puffLocaleMessageSourceHolder.getMessage("INSERT_USERINFO_EXCEPTION"));
                }
            }
            // 把用户信息加入到客户端cookie
            writeSessionAndCookie(loginVo,appUserAuths,session,response);
            // 返回客户端是否设置过密码
            userInfoMap.put("setPassword",StringUtils.isNotBlank(appUserAuths.getAuthToken()));
            return data(userInfoMap);
        }else {
            return bad(ErrorStatus.ERROR_SMS_VALIDATE_CODE);
        }
    }

    /**
     * 用户登出
     * @param request
     * @param response
     * @param session
     * @return
     */
    @GetMapping("logout")
    public BaseResponseVo logout(HttpServletRequest request,HttpServletResponse response, HttpSession session){
        session.removeAttribute(PuffNamedConstant.USER_SESSION_KEY);
        CookieUtils.clearCookie(request,response,PuffNamedConstant.PUFF_COOKIE_NAME);
        return ok();
    }

    /**
     * 重置/初始化 用户登录密码
     * @param registryVo
     * @param bindingResult
     * @param response
     * @param session
     * @return
     */
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

    @GetMapping("{uuid}")
    public BaseResponseVo userInfo(@PathVariable String uuid){
        AppUserProfiles appUserProfiles = new AppUserProfiles();
        appUserProfiles.setUuid(uuid);
        AppUserProfiles user = userService.getUserProfile(appUserProfiles);
        return data(user);
    }
    /**
     * 更新用户信息
     * @param uuid
     * @param appUserProfiles
     * @param bindingResult
     * @param response
     * @return
     */
    @PutMapping("{uuid}")
    public BaseResponseVo updateUserInfo(@PathVariable String uuid,@RequestBody AppUserProfiles appUserProfiles,BindingResult bindingResult,HttpServletResponse response,HttpSession session){
        String sessionUUID = getLoginUser(session).getUuid();
        if(StringUtils.isNotBlank(uuid)){
            appUserProfiles.setUuid(uuid);
        }else {
            appUserProfiles.setUuid(sessionUUID);
        }
        userService.updateUserInfo(appUserProfiles);
        return ok();
    }

    @GetMapping("{uuid}/favorites")
    public BaseResponseVo listFavorites(@PathVariable String uuid,@RequestParam(value = "last_id",required = false) Integer lastId,HttpSession session){
        AppUserFavorites appUserFavorites = new AppUserFavorites();
        String sessionUUID = getLoginUser(session).getUuid();
        if(StringUtils.isNotBlank(uuid)){
            appUserFavorites.setUuid(uuid);
        }else {
            appUserFavorites.setUuid(sessionUUID);
        }
        appUserFavorites.setLastId(lastId);
        List<AppUserFavorites> list = userService.findAllFavorites(appUserFavorites);
        if(list.isEmpty()){
            return bad(ErrorStatus.NO_MORE_RESULT);
        }
        return data(list);
    }


    /**
     * 收藏商品
     * @param appUserFavorites
     * @param bindingResult
     * @param response
     * @return
     */
    @PostMapping("favorite")
    public BaseResponseVo favorite(@RequestBody @Validated  AppUserFavorites appUserFavorites,BindingResult bindingResult,HttpServletResponse response){
        if(userService.insertUserFavorites(appUserFavorites) < 1){
            return fail();
        }else{
            return ok();
        }
    }

    /**
     * 取消收藏商品
     * @param favoritesId
     * @return
     */
    @PutMapping("favorite/{favoritesId}")
    public BaseResponseVo cancelFavorite(@PathVariable Integer favoritesId){
        AppUserFavorites appUserFavorites = new AppUserFavorites();
        appUserFavorites.setId(favoritesId);
        appUserFavorites.setDelFlag(false);
        if(userService.updateUserFavorites(appUserFavorites) < 1){
            return bad(ErrorStatus.NO_VALID_UPDATED);
        }else{
            return ok();
        }
    }

    /**
     * 获取用户所有的收货地址列表
     * @param appUserDeliveryAddress
     * @return
     */
    @GetMapping("{uuid}/deliveryAddresses")
    public BaseResponseVo listDeliveryAddresses(@PathVariable String uuid){
        AppUserDeliveryAddress appUserDeliveryAddress = new AppUserDeliveryAddress();
        appUserDeliveryAddress.setUuid(uuid);
        List<AppUserDeliveryAddress> list = userService.findAllDeliveryAddressByUuid(appUserDeliveryAddress);
        if(list.isEmpty()){
            return bad(ErrorStatus.NO_MORE_RESULT);
        }
        return data(list);
    }

    /**
     * 新增收货地址
     * @param appUserDeliveryAddress
     * @param bindingResult
     * @param response
     * @return
     */
    @PostMapping("deliveryAddress")
    public BaseResponseVo deliveryAddress(@RequestBody @Validated AppUserDeliveryAddress appUserDeliveryAddress,BindingResult bindingResult,HttpServletResponse response){
        AppUserDeliveryAddress deliveryAddress = new AppUserDeliveryAddress();
        deliveryAddress.setUuid(appUserDeliveryAddress.getUuid());
        if(userService.deliveryAddressOverLimitCount(deliveryAddress)){
            return bad(ErrorStatus.OVER_DELIVERY_ADDRESS_LIMIT);
        } else if(userService.addUserDeliveryAddress(appUserDeliveryAddress) < 1){
            return fail();
        }else {
            return ok();
        }
    }

    /**
     * 编辑或者删除收货地址
     * @param deliveryAddressId
     * @param appUserDeliveryAddress
     * @return
     */
    @PutMapping("deliveryAddress/{deliveryAddressId}")
    public BaseResponseVo editDeliveryAddress(@PathVariable Integer deliveryAddressId,@RequestBody(required = false) AppUserDeliveryAddress appUserDeliveryAddress){
        appUserDeliveryAddress.setId(deliveryAddressId);
        if(userService.updateUserDeliveryAddress(appUserDeliveryAddress) < 1){
            return bad(ErrorStatus.NO_VALID_UPDATED);
        }else {
            return ok();
        }
    }

    @PutMapping("deliveryAddress/{deliveryAddressId}/default")
    public BaseResponseVo settingDefaultDeliveryAddress(@PathVariable Integer deliveryAddressId,HttpSession session){
        userService.defaultDeliveryAddress(deliveryAddressId, WebUtils.getSessionAttribute(session,PuffNamedConstant.USER_SESSION_KEY,AppUserAuths.class).getUuid());
        return ok();
    }

    /**
     * 删除用户的收货地址
     * @param deliveryAddressId
     * @return
     */
    @DeleteMapping("deliveryAddress/{deliveryAddressId}")
    public BaseResponseVo deleteDeliveryAddress(@PathVariable Integer deliveryAddressId){
        AppUserDeliveryAddress appUserDeliveryAddress = new AppUserDeliveryAddress();
        appUserDeliveryAddress.setId(deliveryAddressId);
        appUserDeliveryAddress.setDelFlag(MyMapper.DelFlag.DELETED.getStatus());
        if(userService.updateUserDeliveryAddress(appUserDeliveryAddress) < 1){
            return bad(ErrorStatus.NO_VALID_UPDATED);
        }else {
            return ok();
        }
    }
}
