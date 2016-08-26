package net.blissmall.puff.api.product;


import net.blissmall.puff.domain.product.BussProductDetail;
import net.blissmall.puff.domain.product.BussProductDetailSpec;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ProductDetailSpecService {

    List<BussProductDetailSpec> getProductDetailSpec(BussProductDetail bussProductDetail);

}
