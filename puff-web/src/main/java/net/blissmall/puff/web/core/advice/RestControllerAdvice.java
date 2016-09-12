package net.blissmall.puff.web.core.advice;

import net.blissmall.puff.service.constant.ErrorStatus;
import net.blissmall.puff.service.exception.BussException;
import net.blissmall.puff.vo.http.BaseResponseVo;
import net.blissmall.puff.vo.user.UserInfoVo;
import net.blissmall.puff.web.controller.BaseRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;

/**
 * @Author : pigo
 * @Date : 16/4/28 下午5:04
 * @E-mail : zhenglin.zhu@xfxb.net
 */
@ControllerAdvice(annotations = {RestController.class})
public class RestControllerAdvice extends BaseRestController{

    @ModelAttribute
    public String getUuid(HttpSession session){
        UserInfoVo sessionUser = getLoginUser(session);
        if(sessionUser == null || sessionUser.getAppUserAuths() == null){
            return "";
        }
        return sessionUser.getAppUserAuths().getUuid();
    }

    /**
     * 参数校验的异常处理(主要是来源于service层)
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity processValidationException(ConstraintViolationException e){
        String message = e.getConstraintViolations().iterator().next().getMessage();
        return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 请求消息体或者请求参数异常处理
     * @param e
     * @return
     */
    @ExceptionHandler({HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.LENGTH_REQUIRED)
    public BaseResponseVo processRequestParamAndBodyException(Exception e){

        if(e instanceof HttpMessageNotReadableException){
            logger.warn("捕获到 HttpMessageNotReadableException -> ",e);
            return bad(ErrorStatus.INVALID_PARAMS,puffLocaleMessageSourceHolder.getMessage("MISSING_REQUEST_BODY",new Object[]{"body","body"}));
        }else if(e instanceof MissingServletRequestParameterException){
            logger.warn("捕获到 MissingServletRequestParameterException -> ",e);
            return bad(ErrorStatus.INVALID_PARAMS,puffLocaleMessageSourceHolder.getMessage("MISSING_REQUEST_PARAM"));
        }else {
            return bad(ErrorStatus.INVALID_PARAMS);
        }
    }

    /**
     * 请求的content-tyep或者accept不被支持
     * @param e
     * @return
     */
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public BaseResponseVo processRequestParamAndBodyException(HttpMediaTypeNotSupportedException e){
        logger.warn("捕获到 HttpMediaTypeNotSupportedException -> ",e);
        return bad(ErrorStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * 所有的业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BussException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public BaseResponseVo processBussException(BussException e){
        logger.warn("捕获到 BussException -> ",e);
        return bad(e.getErrorStatus());
    }

    /**
     * 服务器内部异常
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponseVo proccessUnhandleException(HttpServletRequest request, HttpServletResponse response, Exception e){
        logger.error("捕获到 Exception -> ",e);
        return fail();
    }

    /**
     * 找不到相应的路由异常
     * @param ex
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public BaseResponseVo handle404(final NoHandlerFoundException ex){
        logger.warn("未找到相应的路由");
        return restRes(ErrorStatus.NOT_FOUND.getCode(),ErrorStatus.NOT_FOUND.getMsg(),null);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
    }
}
