package net.blissmall.puff.api.cart;

import net.blissmall.puff.domain.cart.AppUserCart;
import net.blissmall.puff.domain.user.AppUserAuths;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AppUserCartService {

    List<AppUserCart> getSkuFromSession(HttpSession session);

    void addSkuInSession(HttpSession session, AppUserCart appUserCart);

    /*
    * 先检查是否存在此sku
    * 有则不做处理,用于立即购买
    * */
    void addDistinctSkuInSession(HttpSession session, AppUserCart appUserCart);

    List<AppUserCart> getSkuFromDB(AppUserAuths appUserAuths);

    void addSkuInDB(AppUserCart appUserCart, AppUserAuths appUserAuths);

    int getCartCount(HttpSession session);

    int getCartCount(AppUserAuths appUserAuths);
}
