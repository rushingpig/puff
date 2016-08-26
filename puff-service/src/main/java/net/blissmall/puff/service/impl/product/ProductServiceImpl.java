package net.blissmall.puff.service.impl.product;

import net.blissmall.puff.api.product.ProductService;
import net.blissmall.puff.domain.product.BussProduct;
import net.blissmall.puff.service.impl.BaseService;
import net.blissmall.puff.service.mapper.product.ProductMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductServiceImpl extends BaseService implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public BussProduct getProduct(BussProduct bussProduct) {
        return productMapper.selectOne(bussProduct);
    }
}
