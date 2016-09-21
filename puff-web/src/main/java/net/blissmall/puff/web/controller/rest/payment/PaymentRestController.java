package net.blissmall.puff.web.controller.rest.payment;

import net.blissmall.puff.api.payment.PaymentService;
import net.blissmall.puff.vo.http.BaseResponseVo;
import net.blissmall.puff.vo.payment.PaymentVo;
import net.blissmall.puff.web.controller.BaseRestController;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author : zhuzhenglin
 * @Date : 16/9/20 15:59
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@RestController
@RequestMapping("/pay")
public class PaymentRestController extends BaseRestController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("charge")
    public BaseResponseVo charge(@Validated @RequestBody PaymentVo paymentVo, BindingResult bindingResult){
        Map<String,Object> data = paymentService.charge(paymentVo);
        return data(data);
    }
}
