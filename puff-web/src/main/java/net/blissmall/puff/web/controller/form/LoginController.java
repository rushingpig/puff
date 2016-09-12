package net.blissmall.puff.web.controller.form;

import net.blissmall.puff.api.user.UserService;
import net.blissmall.puff.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

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

}
