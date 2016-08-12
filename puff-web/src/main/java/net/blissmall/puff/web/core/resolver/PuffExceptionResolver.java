package net.blissmall.puff.web.core.resolver;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/10 23:28
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Component("exceptionResolver")
public class PuffExceptionResolver extends DefaultHandlerExceptionResolver {
    @Override
    protected ModelAndView handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        return super.handleHttpRequestMethodNotSupported(ex, request, response, handler);
    }
}
