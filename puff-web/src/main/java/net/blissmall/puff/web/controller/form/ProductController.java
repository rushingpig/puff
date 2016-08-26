package net.blissmall.puff.web.controller.form;

import net.blissmall.puff.api.product.ProductDetailService;
import net.blissmall.puff.api.product.ProductDetailSpecService;
import net.blissmall.puff.api.product.ProductService;
import net.blissmall.puff.domain.product.BussProduct;
import net.blissmall.puff.domain.product.BussProductDetail;
import net.blissmall.puff.domain.product.BussProductDetailSpec;
import net.blissmall.puff.domain.region.DictRegionalism;
import net.blissmall.puff.service.constant.PuffNamedConstant;
import net.blissmall.puff.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Value("${qn.host}")
    private String qnHost;

    @Resource
    private ProductService productService;

    @Resource
    private ProductDetailService productDetailService;

    @Resource
    private ProductDetailSpecService productDetailSpecService;

    @GetMapping("/{productId}")
    public String productDetail(@PathVariable Integer productId, HttpSession session, Model model){
        DictRegionalism dictRegion = (DictRegionalism) session.getAttribute(PuffNamedConstant.USER_REGION);
        BussProduct product = new BussProduct();
        product.setId(productId);
        product.setDelFlag(true);
        product = productService.getProduct(product);
        BussProductDetail productDetail = productDetailService.getProductDetail(product, dictRegion);
        List<BussProductDetailSpec> productDetailSpecs = productDetailSpecService.getProductDetailSpec(productDetail);
        model.addAttribute("qnHost", qnHost);
        model.addAttribute("product", product);
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("productDetailSpecs", productDetailSpecs);
        return "product";
    }

}

