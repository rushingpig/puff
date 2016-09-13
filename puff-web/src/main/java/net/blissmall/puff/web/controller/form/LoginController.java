package net.blissmall.puff.web.controller.form;

import net.blissmall.puff.api.user.UserService;
import net.blissmall.puff.common.utils.CookieUtils;
import net.blissmall.puff.service.constant.PuffNamedConstant;
import net.blissmall.puff.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static net.blissmall.puff.service.constant.PuffNamedConstant.USER_SESSION_KEY;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/10 22:32
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Controller
public class LoginController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 跳转到登录界面进行登录
     * <li>如果自动登录未失效,将进行自动登录</li>
     * @param puffCookie
     * @param model
     * @return
     */
    @GetMapping("login")
    public String preLogin(){
        return "auth-test";
    }

    /**
     * 用户登出
     *
     * @param request
     * @param response
     * @param session
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session , SessionStatus sessionStatus) {
        session.removeAttribute(USER_SESSION_KEY);
        /**
         * 清除掉所有@SessionAtrribute注解存储的模型值
         */
//        sessionStatus.setComplete();
        CookieUtils.clearCookie(request, response, PuffNamedConstant.PUFF_COOKIE_NAME);
        return "redirect:/";
    }

}
