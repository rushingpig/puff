package net.blissmall.puff.service.constant;

/**
 * 工程相关的命名工厂
 * @Author : zhuzhenglin
 * @Date : 16/8/10 17:53
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public class PuffNamedConstant {

    /**
     * 缓存key的分隔符
     */
    public static final String VALIDATE_CODE_SEPERATOR = ":";
    /**
     * 存储在客户端的用户信息cookie名
     */
    public static final String PUFF_COOKIE_NAME = "puff.blissmall";
    /**
     * 存储session的key值
     */
    public final static String USER_SESSION_KEY = "puff.user";
    /**
     * 存储cookie到客户端的加密盐
     */
    public final static String COOKIE_ENCODE_SALT = "puff.cookie.salt";
    /**
     * 存储cookie时的分隔符
     */
    public final static String COOKIE_ENCODE_SEP = VALIDATE_CODE_SEPERATOR;
    /**
     * 用户收货地址的最大限制
     */
    public final static int DEVLIERY_ADDRESS_LIMIT_COUNT = 10;
    /**
     * 用户收藏每页显示条数
     */
    public final static int FAVORITE_PER_PAGE = 4;

}
