package net.blissmall.puff.web.interceptor;

import net.blissmall.puff.common.utils.JSONUtils;
import net.blissmall.puff.common.utils.ServletUtils;
import net.blissmall.puff.common.utils.WebUtils;
import net.blissmall.puff.domain.user.AppUserAuths;
import net.blissmall.puff.service.constant.ErrorStatus;
import net.blissmall.puff.service.constant.PuffNamedConstant;
import net.blissmall.puff.web.controller.BaseRestController;
import org.apache.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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
                response.getWriter().write(JSONUtils.toJsonString(bad(ErrorStatus.NO_AUTHORIZED_REQUEST)));
            }else{
            // pc端
                logger.warn("此次请求:::{}未授权,需要登录",request.getRequestedSessionId());
                request.getRequestDispatcher("/login").forward(request,response);
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
    }
}
