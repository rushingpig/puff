package net.blissmall.puff.api.product;


import net.blissmall.puff.domain.product.BussProductDetail;
import net.blissmall.puff.domain.product.BussProductTemplateData;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ProductTemplateDataService {

    List<BussProductTemplateData> getProductTemplateData(BussProductDetail bussProductDetail);

}
