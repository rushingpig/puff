package net.blissmall.puff.api.product;

import net.blissmall.puff.vo.product.SkuVo;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CartService {

    public List<SkuVo> getSkuInSession(HttpSession session);

    public void addSkuInSession(HttpSession session, SkuVo skuVo);

    /*
    * 先检查是否存在此sku
    * 有则不做处理,用于立即购买
    * */
    public void addDistinctSkuInSession(HttpSession session, SkuVo skuVo);
}
