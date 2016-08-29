package net.blissmall.puff.service.impl.product;

import net.blissmall.puff.api.product.ProductSkuService;
import net.blissmall.puff.domain.product.BussProduct;
import net.blissmall.puff.domain.product.BussProductSku;
import net.blissmall.puff.domain.region.DictRegionalism;
import net.blissmall.puff.service.impl.BaseService;
import net.blissmall.puff.service.mapper.product.ProductSkuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductSkuServiceImpl extends BaseService implements ProductSkuService {

    @Resource
    private ProductSkuMapper productSkuMapper;

    @Override
    public List<BussProductSku> getProductSku(BussProduct bussProduct, DictRegionalism dictRegionalism) {
        BussProductSku productSku = new BussProductSku();
        productSku.setProductId(bussProduct.getId());
        productSku.setRegionalismId(dictRegionalism.getId());
        productSku.setDelFlag(true);
        return productSkuMapper.select(productSku);
    }
}
