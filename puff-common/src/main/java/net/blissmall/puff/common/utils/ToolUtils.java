package net.blissmall.puff.common.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 跟业务相关的工具操作类
 * @Author : zhuzhenglin
 * @Date : 16/8/9 14:09
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public class ToolUtils {

    private static int DEFAULT_CODE_LENGTH = 4;

    private final Logger logger = LoggerFactory.getLogger(ToolUtils.class);

    /**
     * 根据自定义长度获取验证码<br/>
     * 默认长度{@value DEFAULT_CODE_LENGTH}
     * @param codeLength
     * @return 验证码
     */
    public static String getValidateCode(int codeLength){
        codeLength = Math.abs(codeLength);
        if(codeLength < 4){
            codeLength = DEFAULT_CODE_LENGTH;
        }
        return RandomStringUtils.random(codeLength,false,true);
    }

    /**
     * 获取默认长度的验证码
     * @return
     */
    public static String getValidateCode(){
        return getValidateCode(DEFAULT_CODE_LENGTH);
    }

    public static String getCacheKey(String keyPrefix,String middle,String suffix,String seperator){
        return new StringBuilder(keyPrefix).append(seperator).append(middle).append(seperator).append(suffix).toString();
    }
}
