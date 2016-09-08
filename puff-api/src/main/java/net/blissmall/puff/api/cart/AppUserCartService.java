package net.blissmall.puff.api.cart;

import net.blissmall.puff.domain.cart.AppUserCart;
import net.blissmall.puff.domain.user.AppUserAuths;
import net.blissmall.puff.vo.cart.CartVo;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AppUserCartService {

    List<AppUserCart> getSkuFromSession(HttpSession session);


    void addSkuInSession(HttpSession session, AppUserCart appUserCart);

    void resetSkuInSession(HttpSession session, AppUserCart appUserCart);

    void deleteSkuInSession(HttpSession session, AppUserCart appUserCart);


    void addDistinctSkuInSession(HttpSession session, AppUserCart appUserCart);

    List<AppUserCart> getSkuFromDB(AppUserAuths appUserAuths);

    void addSkuInDB(AppUserCart appUserCart, AppUserAuths appUserAuths);

    void addDistinctSkuInDB(AppUserCart appUserCart, AppUserAuths appUserAuths);

    void resetSkuInDB(AppUserCart appUserCart, AppUserAuths appUserAuths);

    void deleteSkuInDB(AppUserCart appUserCart, AppUserAuths appUserAuths);

    int getCartCount(HttpSession session);

    int getCartCount(AppUserAuths appUserAuths);

    List<CartVo> getCartDetails(HttpSession session, List<AppUserCart> carts);

    Integer calcuatePrice(List<CartVo> cartVos);

    void transSkuFromSessionToDB(HttpSession session);

}
