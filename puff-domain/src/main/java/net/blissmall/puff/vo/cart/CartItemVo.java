package net.blissmall.puff.vo.cart;

import net.blissmall.puff.domain.cart.AppUserCart;
import net.blissmall.puff.domain.product.BussProductSku;
import net.blissmall.puff.vo.product.ProductVo;

import java.io.Serializable;

public class CartItemVo implements Serializable{

    private ProductVo productVo;

    private BussProductSku bussProductSku;

    private AppUserCart appUserCart;

    public ProductVo getProductVo() {
        return productVo;
    }

    public void setProductVo(ProductVo productVo) {
        this.productVo = productVo;
    }

    public BussProductSku getBussProductSku() {
        return bussProductSku;
    }

    public void setBussProductSku(BussProductSku bussProductSku) {
        this.bussProductSku = bussProductSku;
    }

    public AppUserCart getAppUserCart() {
        return appUserCart;
    }

    public void setAppUserCart(AppUserCart appUserCart) {
        this.appUserCart = appUserCart;
    }
}
