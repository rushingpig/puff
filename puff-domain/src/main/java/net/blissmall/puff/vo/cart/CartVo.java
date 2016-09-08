package net.blissmall.puff.vo.cart;

import net.blissmall.puff.vo.category.BussProductCategoryVo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartVo implements Serializable{

    private BussProductCategoryVo bussProductCategoryVo;

    List<CartItemVo> cartItemVos = new ArrayList<CartItemVo>();

    public BussProductCategoryVo getBussProductCategoryVo() {
        return bussProductCategoryVo;
    }

    public void setBussProductCategoryVo(BussProductCategoryVo bussProductCategoryVo) {
        this.bussProductCategoryVo = bussProductCategoryVo;
    }

    public List<CartItemVo> getCartItemVos() {
        return cartItemVos;
    }

    public void setCartItemVos(List<CartItemVo> cartItemVos) {
        this.cartItemVos = cartItemVos;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof CartVo) && this.getBussProductCategoryVo().getId().equals(((CartVo)obj).getBussProductCategoryVo().getId());
    }
}