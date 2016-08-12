package net.blissmall.puff.service.impl;

import net.blissmall.puff.common.utils.ToolUtils;
import net.blissmall.puff.service.constant.PuffNamedConstant;
import net.blissmall.puff.vo.sms.SmsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/10 17:18
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public class BaseService {

    @Value("${puff.cache.code.namespace}")
    protected String namespace;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected String getSmsCodeCacheKey(SmsVo smsVo,String namespace){
        return ToolUtils.getCacheKey(namespace,smsVo.getPhoneNum(),smsVo.getSmsType().name(), PuffNamedConstant.VALIDATE_CODE_SEPERATOR);
    }

}
