package net.blissmall.puff.web.core.aop;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.blissmall.puff.service.constant.ErrorStatus;
import net.blissmall.puff.web.controller.BaseRestController;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

/**
 * @Author : pigo
 * @Date : 16/4/23 下午2:53
 * @E-mail : zhenglin.zhu@xfxb.net
 */
@Aspect
@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class RestControllerAOP extends BaseRestController {

    @Pointcut("execution(* net.blissmall.puff.web.controller.rest..*(..))")
    private void controllerAspectMethod() {
    }

    /**
     * 当参数校验失败时,通过aspecj统一处理并返回,省去了在每个controller里都写逻辑判断
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("controllerAspectMethod()")
    public Object aspectControllerBeanValidation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletResponse response = null;
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Annotation[] annotations = methodSignature.getMethod().getAnnotations();
        boolean doAOP = false;
        for(Annotation a : annotations){
            if(a instanceof GetMapping ||
                    a instanceof PostMapping ||
                    a instanceof DeleteMapping ||
                    a instanceof PutMapping ||
                    a instanceof PatchMapping ||
                    a instanceof RequestMapping){
                doAOP = true;
                break;
            }
        }
        if(!doAOP){
            logger.warn(proceedingJoinPoint.getSignature().getDeclaringTypeName() + " 将不会被自定义AOP拦截...");
            return proceedingJoinPoint.proceed();
        }
        List<Map<String,String>> errList = Lists.newArrayList();
        boolean hasErros = false;
        if(!ArrayUtils.isEmpty(proceedingJoinPoint.getArgs())){
            for (Object o : proceedingJoinPoint.getArgs()) {
                if(o instanceof HttpServletResponse){
                    response = (HttpServletResponse) o;
                }else if (o instanceof BindingResult) {
                    BindingResult bindingResult = (BindingResult)o;
                    if(bindingResult.hasFieldErrors()){
                        hasErros = true;
                        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
                        Map<String,String> errMap = null;
                        for(FieldError fieldError : fieldErrorList){
                            errMap = Maps.newHashMap();
                            errMap.put("field",fieldError.getField());
                            errMap.put("value",fieldError.getRejectedValue() == null ? "" :fieldError.getRejectedValue().toString());
                            errMap.put("info",fieldError.getDefaultMessage());
                            errList.add(errMap);
                        }
                    }
                }
            }
            if(hasErros){
                if(null != response){
                    response.setStatus(HttpStatus.BAD_REQUEST.value());
                }
                return bad(ErrorStatus.INVALID_PARAMS,errList);
            }
        }
        return proceedingJoinPoint.proceed();
    }
}
