package net.blissmall.puff.web.controller.form.payment;

import net.blissmall.puff.common.utils.ZxingUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * @Author : zhuzhenglin
 * @Date : 16/9/20 21:31
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Controller
@RequestMapping("/pay")
public class PaymentController {

    @GetMapping("charge/index")
    public String chargeIndex(){
        return "payment/payment";
    }

    /**
     * 支付成功的跳转页面
     * @return
     */
    @GetMapping("charge/success")
    public String chargeSuccess(){
        return "payment/payment-success";
    }

    @GetMapping("qr/alipay")
    public void qrAlipay(HttpServletResponse response) throws Exception{
        OutputStream os = response.getOutputStream();
        ZxingUtils.encodeToStream("https://qr.alipay.com/bax04005rjgmdcaoblh9005c",182,183,os);
    }
}
