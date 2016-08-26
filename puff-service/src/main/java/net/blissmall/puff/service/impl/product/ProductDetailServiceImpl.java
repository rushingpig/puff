package net.blissmall.puff.service.impl.product;

import net.blissmall.puff.api.product.ProductDetailService;
import net.blissmall.puff.domain.product.BussProduct;
import net.blissmall.puff.domain.product.BussProductDetail;
import net.blissmall.puff.domain.region.DictRegionalism;
import net.blissmall.puff.service.impl.BaseService;
import net.blissmall.puff.service.mapper.product.ProductDetailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/7 00:07
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Service
public class ProductDetailServiceImpl extends BaseService implements ProductDetailService {

    @Resource
    private ProductDetailMapper productDetailMapper;

    @Override
    public BussProductDetail getProductDetail(BussProduct bussProduct, DictRegionalism dictRegionalism) {
        BussProductDetail productDetail = new BussProductDetail();
        productDetail.setProductId(bussProduct.getId());
        productDetail.setRegionalismId(dictRegionalism.getId());
        productDetail.setDelFlag(true);
        return productDetailMapper.selectOne(productDetail);
    }
}
