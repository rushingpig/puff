package net.blissmall.puff.web.controller.form.product;

import net.blissmall.puff.api.product.CartService;
import net.blissmall.puff.vo.product.SkuVo;
import net.blissmall.puff.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController extends BaseController {

    @Resource
    private CartService cartServcie;

    /*
    * 购物车页面
    * */
    @GetMapping("/cart")
    public String goCart(HttpSession session, Model model){
        List<SkuVo> carts = cartServcie.getSkuInSession(session);
        model.addAttribute("carts", carts);
        return "cart";
    }

    /*
    * 立即购买
    * 加入购物车后跳重定向至购物车页面
    * */
    @GetMapping("/cart/buy")
    public String buy(@RequestParam("skuId") Integer skuId,@RequestParam("amount") Integer amount, HttpSession session){
        SkuVo skuVo = new SkuVo(skuId, amount);
        cartServcie.addDistinctSkuInSession(session, skuVo);
        return "redirect:/cart";
    }


}

