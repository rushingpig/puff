package net.blissmall.puff.api.product;


import net.blissmall.puff.domain.product.BussProduct;
import net.blissmall.puff.domain.product.BussProductDetail;
import net.blissmall.puff.domain.region.DictRegionalism;
import org.springframework.validation.annotation.Validated;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/7 00:06
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Validated
public interface ProductDetailService {

    BussProductDetail getProductDetail(BussProduct bussProduct, DictRegionalism dictRegionalism);

}
