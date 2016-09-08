package net.blissmall.puff.service.mapper.product;

import net.blissmall.puff.core.mapper.MyMapper;
import net.blissmall.puff.domain.product.BussProduct;
import net.blissmall.puff.vo.product.ProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper extends MyMapper<BussProduct> {

    List<ProductVo> getProductList(@Param("categoryId") int categoryId, @Param("regionalismId") int regionalismId);

    ProductVo getProduct(@Param("bussProduct") BussProduct bussProduct,@Param("regionalismId") int regionalismId);
}