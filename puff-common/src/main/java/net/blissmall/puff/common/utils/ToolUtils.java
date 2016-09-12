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

    private final static String DB_NULL_VALUE = "NONE";

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

    /**
     * 获取缓存key值
     * @param keyPrefix
     * @param middle
     * @param suffix
     * @param seperator
     * @return
     */
    public static String getCacheKey(String keyPrefix,String middle,String suffix,String seperator){
        return new StringBuilder(keyPrefix).append(seperator).append(middle).append(seperator).append(suffix).toString();
    }

    /**
     * 将IP转换成整型存储到数据库,提高性能(只针对于IP V4)
     * @param ipStr
     * @return
     */
    public static long ip2Long(String ipStr) {
        String[] ip = ipStr.split("\\.");
        return (Long.valueOf(ip[0]) << 24) + (Long.valueOf(ip[1]) << 16)
                + (Long.valueOf(ip[2]) << 8) + Long.valueOf(ip[3]);
    }

    /**
     * 将转换后的整型转换成IP字符串(只针对于IP V4)
     * @param ipLong
     * @return
     */
    public static String long2Ip(long ipLong) {
        StringBuilder ip = new StringBuilder();
        ip.append(ipLong >>> 24).append(".");
        ip.append((ipLong >>> 16) & 0xFF).append(".");
        ip.append((ipLong >>> 8) & 0xFF).append(".");
        ip.append(ipLong & 0xFF);
        return ip.toString();
    }

    /**
     * 当数据库字段列值为"NONE"默认值时,即表示为空
     * @param dbValue
     * @return
     */
    public static Object purgeDBColumnValue(Object dbValue){
        if(dbValue == null){
            return "";
        }
        if(dbValue instanceof String){
            String value = (String)dbValue;
            if((StringUtils.isNotBlank(value) && StringUtils.equals("NONE",value))){
                return "";
            }
        }
        return dbValue;
    }

    /**
     * 获取性别名称
     * @param sex
     * @return
     */
    public static String getSexName(String sex){
        if(StringUtils.equals("1",sex)){
            return "男";
        }else if(StringUtils.equals("2",sex)){
            return "女";
        }else{
            return "未知";
        }
    }

    /**
     * 获取可视化的省市区详细地址
     * @param province
     * @param city
     * @param regionalism
     * @return
     */
    public static String getAddress(Object province,Object city,Object regionalism,Object address){
        return (purgeDBColumnValue(province) + " " + purgeDBColumnValue(city) + " " + purgeDBColumnValue(regionalism) + " " + purgeDBColumnValue(address)).trim();
    }
}
