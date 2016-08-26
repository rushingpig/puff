package net.blissmall.puff.api.product;


import net.blissmall.puff.domain.product.BussProduct;
import org.springframework.validation.annotation.Validated;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/7 00:06
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Validated
public interface ProductService {

    BussProduct getProduct(BussProduct bussProduct);

}
