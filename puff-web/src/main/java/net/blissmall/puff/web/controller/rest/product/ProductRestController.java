package net.blissmall.puff.web.controller.rest.product;

import net.blissmall.puff.api.product.ProductDetailService;
import net.blissmall.puff.api.product.ProductTemplateDataService;
import net.blissmall.puff.domain.product.BussProduct;
import net.blissmall.puff.domain.product.BussProductDetail;
import net.blissmall.puff.domain.product.BussProductTemplateData;
import net.blissmall.puff.domain.region.DictRegionalism;
import net.blissmall.puff.web.controller.BaseRestController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/product/")
public class ProductRestController extends BaseRestController {

    @Resource
    private ProductTemplateDataService productTemplateDataService;

    @Resource
    private ProductDetailService productDetailService;

//    @CrossOrigin(origins = "*",allowedHeaders = {"*"},methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
    @GetMapping("/{productId}/template")
    public List<BussProductTemplateData> getProductTempalteData(@PathVariable Integer productId,HttpSession session){
        BussProduct product = new BussProduct();
        product.setId(productId);
//        DictRegionalism dictRegionalism = (DictRegionalism) session.getAttribute(PuffNamedConstant.USER_REGION);
        DictRegionalism dictRegionalism = new DictRegionalism();
        dictRegionalism.setId(440300);
        BussProductDetail productDetail = productDetailService.getProductDetail(product, dictRegionalism);
        return productTemplateDataService.getProductTemplateData(productDetail);
    }
}
