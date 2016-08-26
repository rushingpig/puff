package net.blissmall.puff.web.core.advice;

import net.blissmall.puff.service.constant.ErrorStatus;
import net.blissmall.puff.service.exception.BussException;
import net.blissmall.puff.vo.http.BaseResponseVo;
import net.blissmall.puff.web.controller.BaseRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

/**
 * @Author : pigo
 * @Date : 16/4/28 下午5:04
 * @E-mail : zhenglin.zhu@xfxb.net
 */
@ControllerAdvice(annotations = {RestController.class})
public class RestControllerAdvice extends BaseRestController{


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity processValidationException(ConstraintViolationException e){
        String message = e.getConstraintViolations().iterator().next().getMessage();
        return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.LENGTH_REQUIRED)
    public BaseResponseVo processRequestParamAndBodyException(Exception e){

        if(e instanceof HttpMessageNotReadableException){
            logger.warn("捕获到 HttpMessageNotReadableException -> ",e);
            return bad(ErrorStatus.INVALID_PARAMS,puffLocaleMessageSourceHolder.getMessage("MISSING_REQUEST_BODY",new Object[]{"body"}));
        }else if(e instanceof MissingServletRequestParameterException){
            logger.warn("捕获到 MissingServletRequestParameterException -> ",e);
            return bad(ErrorStatus.INVALID_PARAMS,puffLocaleMessageSourceHolder.getMessage("MISSING_REQUEST_PARAM"));
        }else {
            return bad(ErrorStatus.INVALID_PARAMS);
        }
    }


    @ExceptionHandler(BussException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public BaseResponseVo processBussException(BussException e){
        logger.warn("捕获到 BussException -> ",e);
        return bad(e.getErrorStatus());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponseVo proccessUnhandleException(HttpServletRequest request, HttpServletResponse response, Exception e){
        logger.error("捕获到 Exception -> ",e);
        return fail();
    }

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
