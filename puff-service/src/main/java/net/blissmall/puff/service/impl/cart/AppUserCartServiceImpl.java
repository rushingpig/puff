package net.blissmall.puff.service.impl.cart;

import net.blissmall.puff.api.cart.AppUserCartService;
import net.blissmall.puff.domain.cart.AppUserCart;
import net.blissmall.puff.domain.user.AppUserAuths;
import net.blissmall.puff.service.constant.PuffNamedConstant;
import net.blissmall.puff.service.mapper.cart.AppUserCartMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class AppUserCartServiceImpl implements AppUserCartService {

    @Resource
    private AppUserCartMapper appUserCartMapper;

    @Override
    public List<AppUserCart> getSkuFromSession(HttpSession session){
        return  (List<AppUserCart>) session.getAttribute(PuffNamedConstant.USER_CART);
    }

    @Override
    public void addSkuInSession(HttpSession session, AppUserCart appUserCart){
        List<AppUserCart> cart = (List<AppUserCart>) session.getAttribute(PuffNamedConstant.USER_CART);
        if(cart == null){
            cart = new ArrayList<>();
        }
        AppUserCart cartItem;
        if(cart.contains(appUserCart)){
            // 购物车存在此sku,则数量增加
            int index = cart.indexOf(appUserCart);
            cartItem = cart.get(index);
            cart.remove(cartItem);
            cartItem.setAmount(cartItem.getAmount() + appUserCart.getAmount());
        }else{
            // 购物车不存在此sku,则增加到购物车
            cartItem = appUserCart;
        }
        cart.add(cartItem);
        session.setAttribute(PuffNamedConstant.USER_CART, cart);
    }

    /*
    * 如果已存在sku,则不对数量进行修改
    * 用于立即购买
    * */
    @Override
    public void addDistinctSkuInSession(HttpSession session, AppUserCart appUserCart) {
        List<AppUserCart> cart = (List<AppUserCart>) session.getAttribute(PuffNamedConstant.USER_CART);
        if(cart == null){
            return;
        }
        boolean exist = cart.contains(appUserCart);
        if(exist){
            return;
        }
        cart.add(appUserCart);
        session.setAttribute(PuffNamedConstant.USER_CART, cart);
    }

    @Override
    public List<AppUserCart> getSkuFromDB(AppUserAuths appUserAuths) {
        AppUserCart appUserCart = new AppUserCart();
        appUserCart.setUuid(appUserAuths.getUuid());
        appUserCart.setDelFlag(true);
        return appUserCartMapper.select(appUserCart);
    }

    @Override
    public void addSkuInDB(AppUserCart appUserCart, AppUserAuths appUserAuths) {
        appUserCart.setUuid(appUserAuths.getUuid());
        appUserCart.setDelFlag(true);
        AppUserCart existCart = appUserCartMapper.selectOne(appUserCart);
        if(existCart != null){
            // 如果购物车已存在此sku,则数量增加
            existCart.setAmount(existCart.getAmount() + appUserCart.getAmount());
            existCart.setUpdatedTime(new Date());
            appUserCartMapper.updateByPrimaryKey(existCart);
        }else{
            // 如果购物车不存在此sku,则增加到购物车
            appUserCart.setCreatedTime(new Date());
            appUserCartMapper.insert(appUserCart);
        }
    }

    @Override
    public int getCartCount(HttpSession session) {
        List<AppUserCart> cart = (List<AppUserCart>) session.getAttribute(PuffNamedConstant.USER_CART);
        Iterator<AppUserCart> iterator = cart.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            AppUserCart cartItem = iterator.next();
            count += cartItem.getAmount();
        }
        return count;
    }

    @Override
    public int getCartCount(AppUserAuths appUserAuths) {
        AppUserCart appUserCart = new AppUserCart();
        appUserCart.setUuid(appUserAuths.getUuid());
        appUserCart.setDelFlag(true);
        List<AppUserCart> cart = appUserCartMapper.select(appUserCart);
        Iterator<AppUserCart> iterator = cart.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            AppUserCart cartItem = iterator.next();
            count += cartItem.getAmount();
        }
        return count;
    }
}
