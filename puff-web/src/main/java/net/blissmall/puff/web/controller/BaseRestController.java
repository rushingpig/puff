package net.blissmall.puff.web.controller;

import net.blissmall.puff.service.constant.ErrorStatus;
import net.blissmall.puff.vo.response.BaseResponseVo;
import net.blissmall.puff.web.core.holder.PuffLocaleMessageSourceHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/4 21:45
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public class BaseRestController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    protected PuffLocaleMessageSourceHolder puffLocaleMessageSourceHolder;



    private BaseResponseVo restRes(String code,String msg,Object data,Object err){
        return new BaseResponseVo(code,msg,data,err);
    }

    protected BaseResponseVo restRes(String code,String msg,Object err){
        return restRes(code,msg,null,err);
    }

    protected BaseResponseVo restRes(Object data){
        return new BaseResponseVo(ErrorStatus.EVERYTHIND_IS_OK.getCode(),ErrorStatus.EVERYTHIND_IS_OK.getMsg(),data,null);
    }

    protected BaseResponseVo ok(){
        return new BaseResponseVo(ErrorStatus.EVERYTHIND_IS_OK.getCode(),ErrorStatus.EVERYTHIND_IS_OK.getMsg(),null,null);
    }

    protected BaseResponseVo fail(){
        return new BaseResponseVo(ErrorStatus.SERVER_ERROR.getCode(),ErrorStatus.SERVER_ERROR.getMsg(),null,null);
    }

    protected BaseResponseVo bad(ErrorStatus errorStatus,Object... dataOrErr){
        if(dataOrErr.length == 0){
            return restRes(errorStatus.getCode(),errorStatus.getMsg(),null);
        }else if(dataOrErr.length == 1){
            return restRes(errorStatus.getCode(),errorStatus.getMsg(),dataOrErr[0]);
        }else{
            return restRes(errorStatus.getCode(),errorStatus.getMsg(),null,dataOrErr);
        }
    }

}
