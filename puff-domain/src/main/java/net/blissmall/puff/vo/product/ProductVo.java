package net.blissmall.puff.vo.product;

import net.blissmall.puff.domain.product.BussProduct;
import net.blissmall.puff.domain.product.BussProductDetail;
import net.blissmall.puff.domain.product.BussProductSku;

public class ProductVo {

    private BussProduct bussProduct;
    private BussProductDetail bussProductDetail;
    private BussProductSku bussProductSku;

    public BussProduct getBussProduct() {
        return bussProduct;
    }

    public void setBussProduct(BussProduct bussProduct) {
        this.bussProduct = bussProduct;
    }

    public BussProductDetail getBussProductDetail() {
        return bussProductDetail;
    }

    public void setBussProductDetail(BussProductDetail bussProductDetail) {
        this.bussProductDetail = bussProductDetail;
    }

    public BussProductSku getBussProductSku() {
        return bussProductSku;
    }

    public void setBussProductSku(BussProductSku bussProductSku) {
        this.bussProductSku = bussProductSku;
    }
}
