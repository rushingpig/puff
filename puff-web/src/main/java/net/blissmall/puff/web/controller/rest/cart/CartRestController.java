package net.blissmall.puff.web.controller.rest.cart;

import net.blissmall.puff.api.cart.AppUserCartService;
import net.blissmall.puff.api.user.UserService;
import net.blissmall.puff.domain.cart.AppUserCart;
import net.blissmall.puff.domain.user.AppUserAuths;
import net.blissmall.puff.service.constant.PuffNamedConstant;
import net.blissmall.puff.vo.http.BaseResponseVo;
import net.blissmall.puff.web.controller.BaseRestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class CartRestController extends BaseRestController {

    @Resource
    private AppUserCartService appUserCartService;

    @Resource
    private UserService userService;

    @PostMapping("/cart")
    public @ResponseBody BaseResponseVo addCart(AppUserCart appUserCart, HttpSession session){
        // 购物车数据存入session
        appUserCartService.addSkuInSession(session, appUserCart);
        // 如果是已登录用户,则购物车数据存入DB
        if(userService.userLogin(session)){
            AppUserAuths appUserAuths = (AppUserAuths) session.getAttribute(PuffNamedConstant.USER_SESSION_KEY);
            appUserCartService.addSkuInDB(appUserCart, appUserAuths);
        }
        return ok();
    }

}

