package net.blissmall.puff.web.controller.form.product;

import net.blissmall.puff.api.product.ProductDetailService;
import net.blissmall.puff.api.product.ProductDetailSpecService;
import net.blissmall.puff.api.product.ProductService;
import net.blissmall.puff.api.product.ProductSkuService;
import net.blissmall.puff.domain.product.BussProduct;
import net.blissmall.puff.domain.product.BussProductDetail;
import net.blissmall.puff.domain.product.BussProductDetailSpec;
import net.blissmall.puff.domain.product.BussProductSku;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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

    @Resource
    private ProductSkuService productSkuService;

    @GetMapping("/{productId}")
    public String productDetail(@PathVariable Integer productId, HttpSession session, Model model){
        DictRegionalism dictRegion = (DictRegionalism) session.getAttribute(PuffNamedConstant.USER_REGION);
        BussProduct product = new BussProduct();
        product.setId(productId);
        product.setDelFlag(true);
        product = productService.getProduct(product);
        BussProductDetail productDetail = productDetailService.getProductDetail(product, dictRegion);
        List<BussProductDetailSpec> productDetailSpecs = productDetailSpecService.getProductDetailSpec(productDetail);
        List<List<BussProductDetailSpec>> specList = transToList(productDetailSpecs);
        List<BussProductSku> skus = productSkuService.getProductSku(product, dictRegion);
        model.addAttribute("qnHost", qnHost);
        model.addAttribute("product", product);
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("specList", specList);
        model.addAttribute("skus", skus);
        return "product";
    }

    // 为了方便渲染,将productDetailSpecs转为二维数组
    private List<List<BussProductDetailSpec>> transToList(List<BussProductDetailSpec> productDetailSpecs){
        List<List<BussProductDetailSpec>> lists = new ArrayList();
        ListIterator<BussProductDetailSpec> listIterator = productDetailSpecs.listIterator();
        while (listIterator.hasNext()){
            int index = listIterator.nextIndex() / 3;
            int size = lists.size();
            List<BussProductDetailSpec> innerList;
            if(size < index + 1) {
                innerList = new ArrayList<BussProductDetailSpec>();
                innerList.add(listIterator.next());
                lists.add(innerList);
            }else{
                innerList = lists.get(index);
                innerList.add(listIterator.next());
            }
        }
        return lists;
    }
}

