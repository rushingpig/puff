package net.blissmall.puff.service.impl.product;

import net.blissmall.puff.api.product.ProductTemplateDataService;
import net.blissmall.puff.domain.product.BussProductDetail;
import net.blissmall.puff.domain.product.BussProductTemplateData;
import net.blissmall.puff.service.impl.BaseService;
import net.blissmall.puff.service.mapper.product.ProductTemplateDataMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductTemplateDataServiceImpl extends BaseService implements ProductTemplateDataService {

    @Resource
    private ProductTemplateDataMapper productTemplateDataMapper;

    @Override
    public List<BussProductTemplateData> getProductTemplateData(BussProductDetail bussProductDetail) {
        BussProductTemplateData bussProductTemplateData = new BussProductTemplateData();
        bussProductTemplateData.setDetailId(bussProductDetail.getId());
        return productTemplateDataMapper.select(bussProductTemplateData);
    }
}
