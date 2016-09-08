package net.blissmall.puff.service.mapper.cart;

import net.blissmall.puff.core.mapper.MyMapper;
import net.blissmall.puff.domain.cart.AppUserCart;

public interface AppUserCartMapper extends MyMapper<AppUserCart> {

    AppUserCart getAppUserCartBySku(AppUserCart appUserCart);
}
