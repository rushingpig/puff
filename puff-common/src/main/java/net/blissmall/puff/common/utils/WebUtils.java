package net.blissmall.puff.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.Map;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/17 11:04
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public class WebUtils extends org.springframework.web.util.WebUtils {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static <T> T getSessionAttribute(HttpSession session,String key,Class<T> clazz){
        return (T) session.getAttribute(key);
    }

    /**
     * 获取发送rest get请求时的uri
     * @param httpUrl
     * @param queryParams
     * @return
     */
    public static URI getRestQueryURI(String httpUrl, Map<String,?> queryParams){
        if(StringUtils.isBlank(httpUrl)){
            return null;
        }
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(httpUrl);
        if(queryParams != null){
            for(Map.Entry<String,?> entry : queryParams.entrySet()){
                builder = builder.queryParam(entry.getKey(),entry.getValue());
            }
        }
        return builder.build().encode().toUri();
    }

    /**
     * 获取发送rest get请求时的uri
     * @param httpUrl
     * @param queryParams
     * @return
     */
    public static String getRestQueryUriString(String httpUrl,Map<String,?> queryParams){
        return getRestQueryURI(httpUrl,queryParams).toString();
    }
}
