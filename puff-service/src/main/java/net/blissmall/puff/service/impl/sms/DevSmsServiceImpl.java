package net.blissmall.puff.service.impl.sms;

import net.blissmall.puff.api.sms.MultiEnvSmsService;
import net.blissmall.puff.api.sms.SmsService;
import net.blissmall.puff.common.utils.JSONUtils;
import net.blissmall.puff.common.utils.ToolUtils;
import net.blissmall.puff.core.annotation.Development;
import net.blissmall.puff.service.impl.BaseService;
import net.blissmall.puff.vo.sms.RequestForSmsVo;
import net.blissmall.puff.vo.sms.SmsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/11 09:41
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Service
@Development
public class DevSmsServiceImpl extends BaseService implements MultiEnvSmsService {

    @Resource
    private SmsService smsService;

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
        if(logger.isInfoEnabled()){
            logger.info("请求发送短信内容:{}", JSONUtils.toJsonString(requestForSmsVo));
        }
        return true;
    }
}
