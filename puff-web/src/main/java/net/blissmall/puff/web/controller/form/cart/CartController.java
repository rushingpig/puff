package net.blissmall.puff.web.controller.form.cart;

import net.blissmall.puff.api.cart.AppUserCartService;
import net.blissmall.puff.api.user.UserService;
import net.blissmall.puff.domain.cart.AppUserCart;
import net.blissmall.puff.domain.user.AppUserAuths;
import net.blissmall.puff.service.constant.PuffNamedConstant;
import net.blissmall.puff.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController extends BaseController {

    @Resource
    private AppUserCartService appUserCartService;

    @Resource
    private UserService userService;

    /*
    * 购物车页面
    * */
    @GetMapping("/cart")
    public String goCart(HttpSession session, Model model){
        List<AppUserCart> carts;
        if(userService.userLogin(session)){
            // 如果已登录,则从DB获取购物车数据
            AppUserAuths appUserAuths = (AppUserAuths) session.getAttribute(PuffNamedConstant.USER_SESSION_KEY);
            carts = appUserCartService.getSkuFromDB(appUserAuths);
        }else {
            // 如果未登录,则从session获取购物车数据
            carts = appUserCartService.getSkuFromSession(session);
        }
        model.addAttribute("carts", carts);
        return "cart";
    }

    /*
    * 立即购买
    * 加入购物车后跳重定向至购物车页面
    * */
    @GetMapping("/cart/buy")
    public String buy(@RequestParam("skuId") Integer skuId,@RequestParam("amount") Integer amount, HttpSession session){
        AppUserCart appUserCart = new AppUserCart(skuId, amount);
        // 数据存入session
        appUserCartService.addDistinctSkuInSession(session, appUserCart);
        // 如果已登录,则存入DB
        if(userService.userLogin(session)){
            AppUserAuths appUserAuths = (AppUserAuths) session.getAttribute(PuffNamedConstant.USER_SESSION_KEY);
            appUserCartService.addSkuInDB(appUserCart, appUserAuths);
        }
        return "redirect:/cart";
    }


}

