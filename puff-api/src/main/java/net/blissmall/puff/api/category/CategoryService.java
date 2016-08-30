package net.blissmall.puff.api.category;

import net.blissmall.puff.domain.product.BussProduct;
import net.blissmall.puff.domain.region.DictRegionalism;
import net.blissmall.puff.vo.product.ProductVo;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface CategoryService {

    List<ProductVo> getProductList(BussProduct bussProduct, DictRegionalism dictRegionalism);

    List<BussProduct> getProductList(BussProduct bussProduct);

}
