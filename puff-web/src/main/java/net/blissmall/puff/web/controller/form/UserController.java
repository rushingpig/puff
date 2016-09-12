package net.blissmall.puff.web.controller.form;

import net.blissmall.puff.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author : zhuzhenglin
 * @Date : 16/9/7 14:54
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    /**
     * 用户信息
     * @param model
     * @return
     */
    @GetMapping("index")
    public String userIndex(Model model){
        return "profile-complete-userinfo";
    }

    /**
     * 收货地址
     * @return
     */
    @GetMapping("deliveryAddresses/index")
    public String deliveryAddressesIndex(){
        return "profile-address";
    }

    /**
     * 添加收货地址
     * @return
     */
    @GetMapping("deliveryAddress/add")
    public String deliveryAddressesAdd(){
        return "profile-add-address";
    }

    /**
     * 编辑收货地址
     * @return
     */
    @GetMapping("deliveryAddress/edit")
    public String deliveryAddressEdit(){
        return "profile-edit-address";
    }

    /**
     * 收藏列表
     * @return
     */
    @GetMapping("favorites/index")
    public String favoritesIndex(){
        return "profile-favourite";
    }

}
