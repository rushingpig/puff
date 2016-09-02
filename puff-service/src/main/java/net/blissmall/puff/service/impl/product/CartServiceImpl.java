package net.blissmall.puff.service.impl.product;

import net.blissmall.puff.api.product.CartService;
import net.blissmall.puff.service.constant.PuffNamedConstant;
import net.blissmall.puff.vo.product.SkuVo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Override
    public List<SkuVo> getSkuInSession(HttpSession session){
        return  (List<SkuVo>) session.getAttribute(PuffNamedConstant.USER_CART);
    }

    @Override
    public void addSkuInSession(HttpSession session, SkuVo skuVo){
        List<SkuVo> cart = (List<SkuVo>) session.getAttribute(PuffNamedConstant.USER_CART);
        if(cart == null){
            cart = new ArrayList<>();
        }
        SkuVo cartItem;
        if(cart.contains(skuVo)){
            // 购物车存在此sku,则数量增加
            int index = cart.indexOf(skuVo);
            cartItem = cart.get(index);
            cart.remove(cartItem);
            cartItem.setAmount(cartItem.getAmount() + skuVo.getAmount());
        }else{
            // 购物车不存在此sku,则增加到购物车
            cartItem = skuVo;
        }
        cart.add(cartItem);
        session.setAttribute(PuffNamedConstant.USER_CART, cart);
    }

    @Override
    public void addDistinctSkuInSession(HttpSession session, SkuVo skuVo) {
        List<SkuVo> cart = (List<SkuVo>) session.getAttribute(PuffNamedConstant.USER_CART);
        if(cart == null){
            return;
        }
        boolean exist = cart.contains(skuVo);
        if(exist){
            return;
        }
        cart.add(skuVo);
        session.setAttribute(PuffNamedConstant.USER_CART, cart);
    }
}
