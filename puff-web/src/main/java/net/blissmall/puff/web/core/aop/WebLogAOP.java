package net.blissmall.puff.web.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 通过AOP获取所有请求的参数
 * @Author : zhuzhenglin
 * @Date : 16/8/4 00:07
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Aspect
@Component
@Order(5)
@Profile("dev")
public class WebLogAOP {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* net.blissmall.puff.web.module.controller..*(..))")
    public void webLogAspectMethod(){}

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Before("webLogAspectMethod()")
    public void doBefore(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        logger.info("WebLogAspect.doBefore()");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();


        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        //获取所有参数方法一：
        Enumeration<String> enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            if(logger.isDebugEnabled()){
                logger.debug(paraName+": "+request.getParameter(paraName));
            }
        }
    }

    @AfterReturning("webLogAspectMethod()")
    public void  doAfterReturning(JoinPoint joinPoint){
        if(logger.isDebugEnabled()){
            // 处理完请求，返回内容
            logger.debug("WebLogAspect.doAfterReturning()");
            logger.debug("耗时（毫秒） : " + (System.currentTimeMillis() - startTime.get()));
        }
    }

}
