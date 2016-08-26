package net.blissmall.puff.service.impl.product;

import net.blissmall.puff.api.product.ProductDetailSpecService;
import net.blissmall.puff.domain.product.BussProductDetail;
import net.blissmall.puff.domain.product.BussProductDetailSpec;
import net.blissmall.puff.service.impl.BaseService;
import net.blissmall.puff.service.mapper.product.ProductDetailSpecMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductDetailSpecServiceImpl extends BaseService implements ProductDetailSpecService {

    @Resource
    private ProductDetailSpecMapper productDetailSpecMapper;

    @Override
    public List<BussProductDetailSpec> getProductDetailSpec(BussProductDetail bussProductDetail) {
        BussProductDetailSpec bussProductDetailSpec = new BussProductDetailSpec();
        bussProductDetailSpec.setDetailId(bussProductDetail.getId());
        return productDetailSpecMapper.select(bussProductDetailSpec);
    }
}
