package net.blissmall.puff.api.sms;

import net.blissmall.puff.vo.sms.SmsVo;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/11 09:38
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public interface MultiEnvSmsService {

    boolean sendSms(SmsVo smsVo);
}
