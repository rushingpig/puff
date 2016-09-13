package net.blissmall.puff.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author : zhuzhenglin
 * @Date : 16/9/13 15:19
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Controller
public class IndexController {

    /**
     * 首页页面控制器
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
