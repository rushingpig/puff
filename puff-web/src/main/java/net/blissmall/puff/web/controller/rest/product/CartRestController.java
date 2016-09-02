package net.blissmall.puff.web.controller.rest.product;

import net.blissmall.puff.api.product.CartService;
import net.blissmall.puff.vo.http.BaseResponseVo;
import net.blissmall.puff.vo.product.SkuVo;
import net.blissmall.puff.web.controller.BaseRestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class CartRestController extends BaseRestController {

    @Resource
    private CartService cartService;

    @PostMapping("/cart")
    public @ResponseBody BaseResponseVo addCart(SkuVo skuVo, HttpSession session){
        cartService.addSkuInSession(session, skuVo);
        return ok();
    }




}

