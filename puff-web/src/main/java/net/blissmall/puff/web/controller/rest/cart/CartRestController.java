package net.blissmall.puff.web.controller.rest.cart;

import net.blissmall.puff.api.cart.AppUserCartService;
import net.blissmall.puff.api.user.UserService;
import net.blissmall.puff.domain.cart.AppUserCart;
import net.blissmall.puff.domain.user.AppUserAuths;
import net.blissmall.puff.service.constant.PuffNamedConstant;
import net.blissmall.puff.vo.http.BaseResponseVo;
import net.blissmall.puff.web.controller.BaseRestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class CartRestController extends BaseRestController {

    /*
    * 购物车数据处理方式:
    * 1、如果未登录,则存入session,从session中获取购物车
    * 2、如果已登录,则存入DB,从DB获取购物车
    * 3、登录操作,session中购物车数据同步至DB
    * 4、注销操作,清除session中购物车数据
    * */

    @Resource
    private AppUserCartService appUserCartService;

    @Resource
    private UserService userService;

    /*
    * 加入购物车
    * 如果不存在sku,则新增sku和数量
    * 如果存在sku,则新增数量
    * */
    @PostMapping("/cart/add")
    public @ResponseBody BaseResponseVo addCart(@RequestBody AppUserCart appUserCart, HttpSession session){
        if (userService.userLogin(session)) {
            // 如果是已登录用户,则购物车数据存入DB
            AppUserAuths appUserAuths = (AppUserAuths) session.getAttribute(PuffNamedConstant.USER_SESSION_KEY);
            appUserCartService.addSkuInDB(appUserCart, appUserAuths);
        } else {
            // 如果是未登录用户,购物车数据存入session
            appUserCartService.addSkuInSession(session, appUserCart);
        }
        return ok();
    }

    /*
    * 重设购物车数量
    * 如果存在sku,则重设数量
    * 如果不存在sku,则不错任何操作
    * */
    @PostMapping("/cart/reset")
    public @ResponseBody BaseResponseVo resetCart(@RequestBody AppUserCart appUserCart, HttpSession session){
        if (userService.userLogin(session)) {
            // 如果是已登录用户,则重设DB中购物车数据
            AppUserAuths appUserAuths = (AppUserAuths) session.getAttribute(PuffNamedConstant.USER_SESSION_KEY);
            appUserCartService.resetSkuInDB(appUserCart, appUserAuths);
        } else {
            // 重设session中购物车数据
            appUserCartService.resetSkuInSession(session, appUserCart);
        }
        return ok();
    }

    /*
    * 删除购物车sku
    * */
    @PostMapping("/cart/delete")
    public @ResponseBody BaseResponseVo deleteCart(@RequestBody AppUserCart appUserCart, HttpSession session){
        if (userService.userLogin(session)) {
            // 如果是已登录用户,则重设DB中购物车数据
            AppUserAuths appUserAuths = (AppUserAuths) session.getAttribute(PuffNamedConstant.USER_SESSION_KEY);
            appUserCartService.deleteSkuInDB(appUserCart, appUserAuths);
        } else {
            // 重设session中购物车数据
            appUserCartService.deleteSkuInSession(session, appUserCart);
        }
        return ok();
    }

}

