package net.blissmall.puff.web.interceptor;

import net.blissmall.puff.common.utils.JSONUtils;
import net.blissmall.puff.common.utils.ServletUtils;
import net.blissmall.puff.common.utils.WebUtils;
import net.blissmall.puff.domain.user.AppUserAuths;
import net.blissmall.puff.service.constant.ErrorStatus;
import net.blissmall.puff.service.constant.PuffNamedConstant;
import net.blissmall.puff.vo.http.BaseResponseVo;
import net.blissmall.puff.web.controller.BaseRestController;
import net.blissmall.puff.web.core.holder.PuffLocaleMessageSourceHolder;
import org.apache.commons.io.Charsets;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录拦截器
 * @Author : zhuzhenglin
 * @Date : 16/8/17 14:11
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public class LoginInterceptor extends BaseRestController implements HandlerInterceptor {

    @Resource
    private PuffLocaleMessageSourceHolder puffLocaleMessageSourceHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI(); //请求完整路径，可用于登陆后跳转
        String contextPath = request.getContextPath();  //项目下完整路径
        String url = requestUri.substring(contextPath.length()); //请求页面
        AppUserAuths appUserAuths = WebUtils.getSessionAttribute(request.getSession(), PuffNamedConstant.USER_SESSION_KEY,AppUserAuths.class);
        // 未登录
        if(appUserAuths == null){
            // ajax请求或者移动端
            if(ServletUtils.isAjaxRequest(request)){
                logger.warn("此次ajax请求:::{}未授权,需要登录",request.getRequestedSessionId());
                response.setStatus(HttpStatus.SC_UNAUTHORIZED);
                BaseResponseVo baseResponseVo = restRes(code(ErrorStatus.NO_AUTHORIZED_REQUEST,puffLocaleMessageSourceHolder),msg(ErrorStatus.NO_AUTHORIZED_REQUEST,puffLocaleMessageSourceHolder),null);
                response.setCharacterEncoding(Charsets.UTF_8.name());
                // 必须要加上content-type，否则容易产生乱码
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                String resStr =JSONUtils.toJsonString(baseResponseVo);
                response.getWriter().write(resStr);
                response.getWriter().close();
            }else{
            // pc端
                logger.warn("此次请求:::{}未授权,需要登录",request.getRequestedSessionId());
                response.sendRedirect(contextPath + "/login");
            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        response.setCharacterEncoding(Charsets.UTF_8.name());
    }
}
