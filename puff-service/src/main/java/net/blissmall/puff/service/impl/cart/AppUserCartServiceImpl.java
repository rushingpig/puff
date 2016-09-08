package net.blissmall.puff.service.impl.cart;

import net.blissmall.puff.api.cart.AppUserCartService;
import net.blissmall.puff.domain.cart.AppUserCart;
import net.blissmall.puff.domain.product.BussProduct;
import net.blissmall.puff.domain.category.BussProductCategory;
import net.blissmall.puff.domain.product.BussProductSku;
import net.blissmall.puff.domain.region.DictRegionalism;
import net.blissmall.puff.domain.user.AppUserAuths;
import net.blissmall.puff.service.constant.PuffNamedConstant;
import net.blissmall.puff.service.mapper.cart.AppUserCartMapper;
import net.blissmall.puff.service.mapper.category.BussProductCategoryMapper;
import net.blissmall.puff.service.mapper.product.ProductMapper;
import net.blissmall.puff.service.mapper.product.ProductSkuMapper;
import net.blissmall.puff.vo.cart.CartItemVo;
import net.blissmall.puff.vo.cart.CartVo;
import net.blissmall.puff.vo.category.BussProductCategoryVo;
import net.blissmall.puff.vo.product.ProductVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class AppUserCartServiceImpl implements AppUserCartService {

    @Resource
    private AppUserCartMapper appUserCartMapper;

    @Resource
    private ProductSkuMapper productSkuMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private BussProductCategoryMapper bussProductCategoryMapper;

    @Override
    public List<AppUserCart> getSkuFromSession(HttpSession session){
        return  (List<AppUserCart>) session.getAttribute(PuffNamedConstant.USER_CART);
    }

    /*
    * 购物车中存在sku,则修改sku数量
    * 购物车不存在此sku,则增加到购物车
    * */
    @Override
    public void addSkuInSession(HttpSession session, AppUserCart appUserCart){
        List<AppUserCart> cart = (List<AppUserCart>) session.getAttribute(PuffNamedConstant.USER_CART);
        if(cart == null){
            cart = new ArrayList<>();
        }
        AppUserCart cartItem;
        if(cart.contains(appUserCart)){
            // 购物车存在此sku,则数量增加
            int index = cart.indexOf(appUserCart);
            cartItem = cart.get(index);
            cartItem.setAmount(cartItem.getAmount() + appUserCart.getAmount());
        }else{
            // 购物车不存在此sku,则增加到购物车
            cartItem = appUserCart;
            cart.add(cartItem);
        }
        session.setAttribute(PuffNamedConstant.USER_CART, cart);
    }

    /*
    * 购物车中存在sku,则重设sku数量
    * */
    @Override
    public void resetSkuInSession(HttpSession session, AppUserCart appUserCart) {
        List<AppUserCart> cart = (List<AppUserCart>) session.getAttribute(PuffNamedConstant.USER_CART);
        if(cart == null){
            return;
        }
        int index = cart.indexOf(appUserCart);
        if (index < 0) {
            return;
        }
        AppUserCart cartItem = cart.get(index);
        cartItem.setAmount(appUserCart.getAmount());
        session.setAttribute(PuffNamedConstant.USER_CART, cart);
    }

    @Override
    public void deleteSkuInSession(HttpSession session, AppUserCart appUserCart) {
        List<AppUserCart> cart = (List<AppUserCart>) session.getAttribute(PuffNamedConstant.USER_CART);
        if(cart == null){
            return;
        }
        int index = cart.indexOf(appUserCart);
        if (index < 0) {
            return;
        }
        cart.remove(index);
        session.setAttribute(PuffNamedConstant.USER_CART, cart);
    }

    /*
    * 先检查是否存在此sku
    * 如果已存在sku,则不对数量进行修改
    * 用于立即购买
    * */
    @Override
    public void addDistinctSkuInSession(HttpSession session, AppUserCart appUserCart) {
        List<AppUserCart> cart = (List<AppUserCart>) session.getAttribute(PuffNamedConstant.USER_CART);
        if(cart == null){
            cart = new ArrayList<>();
        }
        boolean exist = cart.contains(appUserCart);
        if(exist){
            return;
        }
        cart.add(appUserCart);
        session.setAttribute(PuffNamedConstant.USER_CART, cart);
    }

    @Override
    public List<AppUserCart> getSkuFromDB(AppUserAuths appUserAuths) {
        AppUserCart appUserCart = new AppUserCart();
        appUserCart.setUuid(appUserAuths.getUuid());
        appUserCart.setDelFlag(true);
        return appUserCartMapper.select(appUserCart);
    }

    @Override
    public void addSkuInDB(AppUserCart appUserCart, AppUserAuths appUserAuths) {
        appUserCart.setUuid(appUserAuths.getUuid());
        appUserCart.setDelFlag(true);
        AppUserCart existCart = appUserCartMapper.selectOne(appUserCart);
        if(existCart != null){
            // 如果购物车已存在此sku,则数量增加
            existCart.setAmount(existCart.getAmount() + appUserCart.getAmount());
            existCart.setUpdatedTime(new Date());
            appUserCartMapper.updateByPrimaryKey(existCart);
        }else{
            // 如果购物车不存在此sku,则增加到购物车
            appUserCart.setCreatedTime(new Date());
            appUserCartMapper.insert(appUserCart);
        }
    }

    @Override
    public void addDistinctSkuInDB(AppUserCart appUserCart, AppUserAuths appUserAuths) {
        appUserCart.setUuid(appUserAuths.getUuid());
        appUserCart.setDelFlag(true);
        AppUserCart existCart = appUserCartMapper.selectOne(appUserCart);
        if(existCart == null){
            // 如果购物车不存在此sku,则增加到购物车
            appUserCart.setCreatedTime(new Date());
            appUserCartMapper.insert(appUserCart);
        }
    }

    @Override
    public void resetSkuInDB(AppUserCart appUserCart, AppUserAuths appUserAuths) {
        appUserCart.setUuid(appUserAuths.getUuid());
        appUserCart.setDelFlag(true);
        AppUserCart appUserCartInDB = appUserCartMapper.getAppUserCartBySku(appUserCart);
        if (appUserCartInDB == null) {
            return;
        }
        appUserCartInDB.setAmount(appUserCart.getAmount());
        appUserCartMapper.updateByPrimaryKey(appUserCartInDB);
    }

    @Override
    public void deleteSkuInDB(AppUserCart appUserCart, AppUserAuths appUserAuths) {
        appUserCart.setUuid(appUserAuths.getUuid());
        appUserCart.setDelFlag(true);
        AppUserCart appUserCartInDB = appUserCartMapper.getAppUserCartBySku(appUserCart);
        appUserCartInDB.setDelFlag(false);
        appUserCartMapper.updateByPrimaryKey(appUserCartInDB);
    }

    @Override
    public int getCartCount(HttpSession session) {
        List<AppUserCart> cart = (List<AppUserCart>) session.getAttribute(PuffNamedConstant.USER_CART);
        Iterator<AppUserCart> iterator = cart.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            AppUserCart cartItem = iterator.next();
            count += cartItem.getAmount();
        }
        return count;
    }

    @Override
    public int getCartCount(AppUserAuths appUserAuths) {
        AppUserCart appUserCart = new AppUserCart();
        appUserCart.setUuid(appUserAuths.getUuid());
        appUserCart.setDelFlag(true);
        List<AppUserCart> cart = appUserCartMapper.select(appUserCart);
        Iterator<AppUserCart> iterator = cart.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            AppUserCart cartItem = iterator.next();
            count += cartItem.getAmount();
        }
        return count;
    }

    @Override
    public List<CartVo> getCartDetails(HttpSession session, List<AppUserCart> carts) {
        if (carts == null || carts.size() == 0) {
            return null;
        }
        List<CartItem> cartItems = new ArrayList<>();
        DictRegionalism dictRegionalism = (DictRegionalism) session.getAttribute(PuffNamedConstant.USER_REGION);
        for(AppUserCart cart : carts){
            cartItems.add(this.getCartDetail(cart, dictRegionalism));
        }
        return formatCartToCartVo(cartItems);
    }

    @Override
    public Integer calcuatePrice(List<CartVo> cartVos) {
        Integer price = 0;
        if (cartVos == null || cartVos.size() == 0){
            return price;
        }
        for (CartVo cartVo : cartVos) {
            price += this.calculateItemPrice(cartVo.getCartItemVos());
        }
        return price;
    }

    /*
    * 用于登录后将session购物车同步至DB
    * */
    @Override
    public void transSkuFromSessionToDB(HttpSession session) {
        List<AppUserCart> cart = (List<AppUserCart>) session.getAttribute(PuffNamedConstant.USER_CART);
        for (AppUserCart appUserCart : cart) {
            AppUserCart appUserCartInDB = appUserCartMapper.selectOne(appUserCart);
            if (appUserCartInDB == null) {
                // DB不存在此sku,同步至DB
                appUserCartMapper.insert(appUserCart);
            } else {
                // DB存在此sku,仅更新数量
                appUserCartInDB.setAmount(appUserCart.getAmount());
                appUserCartMapper.updateByPrimaryKey(appUserCartInDB);
            }
        }
    }

    private Integer calculateItemPrice(List<CartItemVo> cartItemVos){
        Integer price = 0;
        for (CartItemVo cartItemVo : cartItemVos) {
            price += cartItemVo.getBussProductSku().getPrice() * cartItemVo.getAppUserCart().getAmount();
        }
        return price;
    }

    private CartItem getCartDetail(AppUserCart appUserCart, DictRegionalism dictRegionalism){
        CartItem cartItem = new CartItem();
        // appUserCart数据
        cartItem.setAppUserCart(appUserCart);
        // sku详情
        BussProductSku bussProductSku = new BussProductSku();
        bussProductSku.setId(appUserCart.getSkuId());
        bussProductSku = productSkuMapper.selectOne(bussProductSku);
        cartItem.setBussProductSku(bussProductSku);
        // product详情
        BussProduct bussProduct = new BussProduct();
        bussProduct.setId(bussProductSku.getProductId());
        ProductVo productVo = productMapper.getProduct(bussProduct, dictRegionalism.getId());
        cartItem.setProductVo(productVo);
        // category详情
        BussProductCategory bussProductCategory = new BussProductCategory();
        bussProductCategory.setId(productVo.getBussProduct().getCategoryId());
        bussProductCategory = bussProductCategoryMapper.selectOne(bussProductCategory);
        BussProductCategory parentProductCategory = new BussProductCategory();
        parentProductCategory.setId(bussProductCategory.getParentId());
        BussProductCategoryVo parentProductCategoryVo = bussProductCategoryMapper.getCategoryWithSort(parentProductCategory, dictRegionalism.getId());
        cartItem.setBussProductCategoryVo(parentProductCategoryVo);
        return cartItem;
    }

    private List<CartVo> formatCartToCartVo(List<CartItem> cartItems){
        List<CartVo> cartVos = new ArrayList<>();
        cartItems.forEach(cartItem -> {
            CartVo cartVo = new CartVo();
            cartVo.setBussProductCategoryVo(cartItem.getBussProductCategoryVo());
            if(cartVos.contains(cartVo)){
                int index = cartVos.indexOf(cartVo);
                cartVo = cartVos.get(index);
                cartVo.setBussProductCategoryVo(cartItem.getBussProductCategoryVo());
                CartItemVo cartItemVo = new CartItemVo();
                cartItemVo.setProductVo(cartItem.getProductVo());
                cartItemVo.setBussProductSku(cartItem.getBussProductSku());
                cartItemVo.setAppUserCart(cartItem.getAppUserCart());
                cartVo.getCartItemVos().add(cartItemVo);
            }else{
                CartItemVo cartItemVo = new CartItemVo();
                cartItemVo.setProductVo(cartItem.getProductVo());
                cartItemVo.setBussProductSku(cartItem.getBussProductSku());
                cartItemVo.setAppUserCart(cartItem.getAppUserCart());
                cartVo.getCartItemVos().add(cartItemVo);
                cartVos.add(cartVo);
            }
        });
        cartVos.sort((a, b) -> {
            return a.getBussProductCategoryVo().getSort() > b.getBussProductCategoryVo().getSort() ? 1 : -1;
        });
        return cartVos;
    }

    class CartItem {

        private BussProductCategoryVo bussProductCategoryVo;

        private ProductVo productVo;

        private BussProductSku bussProductSku;

        private AppUserCart appUserCart;

        public BussProductCategoryVo getBussProductCategoryVo() {
            return bussProductCategoryVo;
        }

        public void setBussProductCategoryVo(BussProductCategoryVo bussProductCategoryVo) {
            this.bussProductCategoryVo = bussProductCategoryVo;
        }

        public ProductVo getProductVo() {
            return productVo;
        }

        public void setProductVo(ProductVo productVo) {
            this.productVo = productVo;
        }

        public BussProductSku getBussProductSku() {
            return bussProductSku;
        }

        public void setBussProductSku(BussProductSku bussProductSku) {
            this.bussProductSku = bussProductSku;
        }

        public AppUserCart getAppUserCart() {
            return appUserCart;
        }

        public void setAppUserCart(AppUserCart appUserCart) {
            this.appUserCart = appUserCart;
        }
    }
}
