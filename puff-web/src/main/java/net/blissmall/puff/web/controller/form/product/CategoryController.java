package net.blissmall.puff.web.controller.form.product;

import net.blissmall.puff.api.category.CategoryService;
import net.blissmall.puff.domain.product.BussProduct;
import net.blissmall.puff.domain.region.DictRegionalism;
import net.blissmall.puff.service.constant.PuffNamedConstant;
import net.blissmall.puff.vo.product.ProductVo;
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
@RequestMapping("/category")
public class CategoryController extends BaseController {

    @Value("${qn.host}")
    private String qnHost;

    @Resource
    private CategoryService categoryService;

    @GetMapping("/{categoryId}")
    public String productList(@PathVariable Integer categoryId, HttpSession session, Model model){
        DictRegionalism dictRegion = (DictRegionalism) session.getAttribute(PuffNamedConstant.USER_REGION);
        BussProduct product = new BussProduct();
        product.setCategoryId(categoryId);
        product.setDelFlag(true);
        List<ProductVo> products = categoryService.getProductList(product, dictRegion);
        model.addAttribute("qnHost", qnHost);
        model.addAttribute("products", products);
        return "prolist";
    }
}

