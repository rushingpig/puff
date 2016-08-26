package net.blissmall.puff.service.impl.sms;

import net.blissmall.puff.api.sms.MultiEnvSmsService;
import net.blissmall.puff.api.sms.SmsService;
import net.blissmall.puff.common.utils.ToolUtils;
import net.blissmall.puff.core.annotation.Production;
import net.blissmall.puff.core.annotation.Qa;
import net.blissmall.puff.service.impl.BaseService;
import net.blissmall.puff.vo.sms.RequestForSmsVo;
import net.blissmall.puff.vo.sms.SmsVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/11 09:49
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Service
@Production
@Qa
public class ProSmsServiceImpl extends BaseService implements MultiEnvSmsService {
    @Resource
    private SmsService smsService;

    @Value("${puff.sms.host}")
    private String smsHost;


    @Resource
    private RestTemplate restTemplate;

    @Override
    public boolean sendSms(SmsVo smsVo) {
        String code = ToolUtils.getValidateCode();
        // 组装需要发送短信的vo
        RequestForSmsVo requestForSmsVo = new RequestForSmsVo();
        requestForSmsVo.setPhone(smsVo.getPhoneNum());
        requestForSmsVo.setMethod(RequestForSmsVo.getRequestSmsMethod(smsVo.getSmsType()));
        RequestForSmsVo.Params params = requestForSmsVo.new Params();
        params.setCode(code);
        requestForSmsVo.setParams(params);
        // 将验证码存入redis缓存
        smsService.cacheValidateCode(getSmsCodeCacheKey(smsVo,namespace),code);
        // 请求发短信服务
        ResponseEntity result = restTemplate.postForEntity(smsHost,requestForSmsVo,String.class);
        if(logger.isDebugEnabled()){
            logger.debug("请求发送短信内容:{}",requestForSmsVo,"\n返回内容:{}",result.getBody());
        }
        return result.getStatusCode() == HttpStatus.OK;
    }
}
