package net.blissmall.puff.api.payment;

import net.blissmall.puff.vo.payment.PaymentVo;

import java.util.Map;

/**
 * @Author : zhuzhenglin
 * @Date : 16/9/20 17:32
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public interface PaymentService {

    Map<String,Object> charge(PaymentVo paymentVo);

}
