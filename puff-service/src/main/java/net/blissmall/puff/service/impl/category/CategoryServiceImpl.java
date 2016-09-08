package net.blissmall.puff.service.impl.category;

import net.blissmall.puff.api.category.CategoryService;
import net.blissmall.puff.domain.product.BussProduct;
import net.blissmall.puff.domain.region.DictRegionalism;
import net.blissmall.puff.service.mapper.product.ProductMapper;
import net.blissmall.puff.vo.product.ProductVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public List<ProductVo> getProductList(BussProduct bussProduct, DictRegionalism dictRegionalism) {
        return productMapper.getProductList(bussProduct.getCategoryId(), dictRegionalism.getId());
    }

    @Override
    public List<BussProduct> getProductList(BussProduct bussProduct) {
        return productMapper.select(bussProduct);
    }
}
