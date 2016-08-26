package net.blissmall.puff.api.sms;

import net.blissmall.puff.vo.sms.SmsVo;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/9 19:29
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Validated
public interface SmsService {


    boolean validateSmsCode(@Valid SmsVo smsVo);

    void cacheValidateCode(String key,String code);

    long getValidateTimeout(SmsVo smsVo);

}
