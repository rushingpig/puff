package net.blissmall.puff.service.impl.sms;

import net.blissmall.puff.api.sms.SmsService;
import net.blissmall.puff.common.utils.StringUtils;
import net.blissmall.puff.service.constant.ErrorStatus;
import net.blissmall.puff.service.exception.BussException;
import net.blissmall.puff.service.impl.BaseService;
import net.blissmall.puff.vo.sms.SmsVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/9 19:41
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Service
@CacheConfig(cacheNames = {"sms"})
public class SmsServiceImpl extends BaseService implements SmsService {

    @Value("${puff.redis.validatecode}")
    private int validateCodeSessionTimeout;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean validateSmsCode(SmsVo smsVo) {
        String key = getSmsCodeCacheKey(smsVo);
        String inputCode = smsVo.getCode();
        String cacheCode = stringRedisTemplate.opsForValue().get(key);
        if(StringUtils.isBlank(cacheCode)){
            throw new BussException(ErrorStatus.SMS_VALIDATE_CODE_EXPIRE);
        }else if(!StringUtils.equals(inputCode,cacheCode)){
            throw new BussException(ErrorStatus.ERROR_SMS_VALIDATE_CODE);
        }
        // 验证完毕后删除对应的缓存
        stringRedisTemplate.delete(key);
        return true;
    }

    @Override
    public void cacheValidateCode(String key,String code) {
        stringRedisTemplate.opsForValue().set(key,code,validateCodeSessionTimeout, TimeUnit.SECONDS);
    }

    @Override
    public long getValidateTimeout(SmsVo smsVo) {
        String key = getSmsCodeCacheKey(smsVo);
        return stringRedisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

}
