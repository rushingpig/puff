package net.blissmall.puff.web.core.advice;

import net.blissmall.puff.vo.user.UserInfoVo;
import net.blissmall.puff.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

/**
 * 所有RestController的切面
 * @Author : pigo
 * @Date : 5/13/16 16:33
 * @E-mail : zhenglin.zhu@xfxb.net
 */
@ControllerAdvice(annotations = {Controller.class})
public class GeneralRestControllerAdvice extends BaseController{

    @ModelAttribute
    public UserInfoVo getUserInfo(HttpSession session){
        return getLoginUser(session);
    }

}
