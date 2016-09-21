package net.blissmall.puff.service.impl.payment;

import com.google.common.collect.Maps;
import net.blissmall.puff.api.payment.PaymentService;
import net.blissmall.puff.service.impl.BaseService;
import net.blissmall.puff.vo.payment.PaymentVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author : zhuzhenglin
 * @Date : 16/9/20 17:38
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Service
public class PaymentServiceImpl extends BaseService implements PaymentService {

    @Resource
    private RestTemplate restTemplate;

    @Value("${puff.pay.charge.host}")
    private String puffPayChargeHost;

    @Override
    public Map<String, Object> charge(PaymentVo paymentVo) {
        paymentVo.setBody("购买榴芒双拼");
        paymentVo.setClient_ip("127.0.0.1");
        paymentVo.setOrder_no("2016010210000001");
        paymentVo.setSubject("榴芒双拼蛋糕");
        Map<String,Object> map = Maps.newHashMap();
        map.put("success_url","http://blissmall.net");
        paymentVo.setExtra(map);
        Map<String,Object> response = restTemplate.postForObject(puffPayChargeHost,paymentVo,Map.class);
        return response;
    }
}
