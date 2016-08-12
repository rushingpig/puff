package net.blissmall.puff.web.controller.form;

import net.blissmall.puff.api.user.UserService;
import net.blissmall.puff.common.utils.CryptoUtils;
import net.blissmall.puff.common.utils.EncodeUtils;
import net.blissmall.puff.common.utils.StringUtils;
import net.blissmall.puff.domain.user.AppUserAuths;
import net.blissmall.puff.service.constant.PuffNamedConstant;
import net.blissmall.puff.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/10 22:32
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 跳转到登录界面进行登录
     * <li>如果自动登录未失效,将进行自动登录</li>
     * @param puffCookie
     * @param model
     * @return
     */
    @GetMapping("preLogin")
    public String preLogin(@CookieValue(name = PuffNamedConstant.PUFF_COOKIE_NAME,required = false) String puffCookie, Model model){
        if(StringUtils.isNotBlank(puffCookie)){
            String cookie = EncodeUtils.decodeBase64String(puffCookie);
            String[] decodeCookie = cookie.split(PuffNamedConstant.COOKIE_ENCODE_SEP);
            if(decodeCookie.length != 3){
                model.addAttribute("errMsg",messageSourceHolder.getMessage("ILLEGAL_COOKIE"));
                return "login";
            }else {
                String username = decodeCookie[0];
                long validTimePoint = Long.valueOf(decodeCookie[1]);
                String md5UsernamePassword = decodeCookie[2];
                if(System.currentTimeMillis() > validTimePoint){
                    model.addAttribute("errMsg",messageSourceHolder.getMessage("EXPIRED_COOKIE"));
                    return "login";
                }
                AppUserAuths appUserAuths = new AppUserAuths();
                appUserAuths.setAuthId(username);
                AppUserAuths user = userService.getUser(appUserAuths);
                String dbUsernamePassword = CryptoUtils.MD5(user.getAuthId() + user.getAuthToken() + PuffNamedConstant.COOKIE_ENCODE_SALT);
                if(!StringUtils.equals(dbUsernamePassword,md5UsernamePassword)){
                    model.addAttribute("errMsg",messageSourceHolder.getMessage("ILLEGAL_COOKIE"));
                    return "login";
                }
            }
            return "redirect:/";
        }else {
            model.addAttribute("errMsg",messageSourceHolder.getMessage("EXPIRED_COOKIE"));
            return "login";
        }
    }

}
