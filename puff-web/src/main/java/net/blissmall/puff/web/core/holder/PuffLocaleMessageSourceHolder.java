package net.blissmall.puff.web.core.holder;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * 国际化信息资源持有者
 * @Author : zhuzhenglin
 * @Date : 16/8/3 23:16
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Component
public class PuffLocaleMessageSourceHolder {

    @Resource
    private MessageSource messageSource;

    public String getMessage(String code){
        return getMessage(code,null);
    }

    public String getMessage(String code,Object[] params){
        return getMessage(code,params,"");
    }

    /**
     *
     * @param code 对应message配置的key
     * @param args
     *      <pre>
     *          对应要填充的数组。
     *          {0} {1,date} {2,time}
     *      </pre>
     * @param defaultMessage 如果key没有设置对应的message,所要填充的默认message
     * @return
     */
    public String getMessage(String code,Object[] args,String defaultMessage){
        //这里使用比较方便的方法，不依赖request.
        Locale locale = LocaleContextHolder.getLocale();
        /*
          另一种获取方式(依赖于request,不推荐):
          Locale locale1= RequestContextUtils.getLocale(request);
         */
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }
}
