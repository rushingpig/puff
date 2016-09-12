package net.blissmall.puff.web.interceptor;

import net.blissmall.puff.api.user.UserService;
import net.blissmall.puff.common.utils.*;
import net.blissmall.puff.domain.user.AppUserAuths;
import net.blissmall.puff.domain.user.AppUserProfiles;
import net.blissmall.puff.service.constant.ErrorStatus;
import net.blissmall.puff.service.constant.PuffNamedConstant;
import net.blissmall.puff.vo.http.BaseResponseVo;
import net.blissmall.puff.vo.user.UserInfoVo;
import net.blissmall.puff.web.controller.BaseRestController;
import net.blissmall.puff.web.core.holder.PuffLocaleMessageSourceHolder;
import org.apache.commons.io.Charsets;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static net.blissmall.puff.common.utils.EncodeUtils.urlDecode;

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
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI(); //请求完整路径，可用于登陆后跳转
        String contextPath = request.getContextPath();  //项目下完整路径
        String url = requestUri.substring(contextPath.length()); //请求页面
        // 1.判断session是否有用户信息
        UserInfoVo userInfoVo = WebUtils.getSessionAttribute(request.getSession(), PuffNamedConstant.USER_SESSION_KEY,UserInfoVo.class);
        if(userInfoVo == null){
            // ajax请求或者移动端
            if(!validateClientCookie(request)){
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
        }
        return true;
    }

    private boolean validateClientCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for(Cookie pcCookie : cookies){
                if(StringUtils.equals(pcCookie.getName(),PuffNamedConstant.PUFF_COOKIE_NAME)){
                    String puffCookie = pcCookie.getValue();
                    if(StringUtils.isNotBlank(puffCookie)) {
                        String cookie = EncodeUtils.decodeBase64String(urlDecode(puffCookie));
                        String[] decodeCookie = cookie.split(PuffNamedConstant.COOKIE_ENCODE_SEP);
                        if (decodeCookie.length != 3) {
                            logger.warn("errMsg :: {}", puffLocaleMessageSourceHolder.getMessage("ILLEGAL_COOKIE"));
                        } else {
                            String username = decodeCookie[0];
                            long validTimePoint = Long.valueOf(decodeCookie[1]);
                            String md5UsernamePassword = decodeCookie[2];
                            if (System.currentTimeMillis() > validTimePoint) {
                                logger.warn("errMsg :: {}", puffLocaleMessageSourceHolder.getMessage("EXPIRED_COOKIE"));
                            }
                            AppUserAuths appUserAuths = new AppUserAuths();
                            appUserAuths.setAuthId(username);
                            AppUserAuths user = userService.getUser(appUserAuths);
                            String dbUsernamePassword = CryptoUtils.MD5(user.getAuthId() + user.getAuthToken() + PuffNamedConstant.COOKIE_ENCODE_SALT);
                            if (!StringUtils.equals(dbUsernamePassword, md5UsernamePassword)) {
                                logger.warn("errMsg :: {}", puffLocaleMessageSourceHolder.getMessage("ILLEGAL_COOKIE"));
                            } else {
                                AppUserProfiles appUserProfiles = new AppUserProfiles();
                                appUserProfiles.setUuid(user.getUuid());
                                appUserProfiles = userService.getUserProfile(appUserProfiles);
                                UserInfoVo userInfoVo = new UserInfoVo(user, appUserProfiles);
                                request.getSession().setAttribute(PuffNamedConstant.USER_SESSION_KEY, userInfoVo);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        response.setCharacterEncoding(Charsets.UTF_8.name());
    }
}
