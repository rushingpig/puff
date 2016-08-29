package net.blissmall.puff.api.product;


import net.blissmall.puff.domain.product.BussProduct;
import net.blissmall.puff.domain.product.BussProductSku;
import net.blissmall.puff.domain.region.DictRegionalism;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/7 00:06
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Validated
public interface ProductSkuService {

    List<BussProductSku> getProductSku(BussProduct bussProduct, DictRegionalism dictRegionalism);

}
