package net.blissmall.puff.web.core.view;

import com.fasterxml.jackson.core.JsonEncoding;
import net.blissmall.puff.common.utils.JSONUtils;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * 自定义的json视图解析器的实现
 * @Author : zhuzhenglin
 * @Date : 16/7/28 15:14
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public class PuffJsonViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        PuffMappingJackson2JsonView view = new PuffMappingJackson2JsonView();
        view.setExtractValueFromSingleKeyModel(false);
        view.setEncoding(JsonEncoding.UTF8);
        view.setPrettyPrint(true);
        view.setObjectMapper(JSONUtils.getInstance());
        return view;
    }
}
