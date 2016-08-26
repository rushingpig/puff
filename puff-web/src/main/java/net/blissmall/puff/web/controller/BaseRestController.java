package net.blissmall.puff.web.controller;

import net.blissmall.puff.common.utils.WebUtils;
import net.blissmall.puff.domain.user.AppUserAuths;
import net.blissmall.puff.service.constant.ErrorStatus;
import net.blissmall.puff.service.constant.PuffNamedConstant;
import net.blissmall.puff.vo.http.BaseResponseVo;
import net.blissmall.puff.web.core.holder.PuffLocaleMessageSourceHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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

    protected AppUserAuths getLoginUser(HttpSession session){
        return WebUtils.getSessionAttribute(session, PuffNamedConstant.USER_SESSION_KEY,AppUserAuths.class);
    }



    protected BaseResponseVo restRes(String code,String msg,Object data,Object err){
        return new BaseResponseVo(code,msg,data,err);
    }

    protected BaseResponseVo restRes(String code,String msg,Object err){
        return restRes(code,msg,null,err);
    }

    protected BaseResponseVo restRes(Object data){
        return new BaseResponseVo(code(ErrorStatus.EVERYTHIND_IS_OK),msg(ErrorStatus.EVERYTHIND_IS_OK),data,null);
    }

    protected BaseResponseVo data(Object data){
        return restRes(data);
    }

    protected BaseResponseVo ok(){
        return restRes(code(ErrorStatus.EVERYTHIND_IS_OK),msg(ErrorStatus.EVERYTHIND_IS_OK),null,null);
    }

    protected BaseResponseVo fail(){
        return bad(ErrorStatus.SERVER_ERROR);
    }

    protected BaseResponseVo fail(Object errMsg){
        return bad(ErrorStatus.SERVER_ERROR,errMsg);
    }

    protected BaseResponseVo bad(ErrorStatus errorStatus,Object... dataOrErr){
        if(dataOrErr.length == 0){
            return restRes(code(errorStatus),msg(errorStatus),null);
        }else if(dataOrErr.length == 1){
            return restRes(code(errorStatus),msg(errorStatus),dataOrErr[0]);
        }else{
            return restRes(code(errorStatus),msg(errorStatus),null,dataOrErr);
        }
    }

    /**
     * 转换错误状态代码
     * @param errorStatus
     * @return
     */
    private String code(ErrorStatus errorStatus){
        if(errorStatus != null){
            return puffLocaleMessageSourceHolder.getMessage(errorStatus.getCode());
        }
        return "";
    }

    /**
     * 转换错误状态信息
     * @param errorStatus
     * @return
     */
    private String msg(ErrorStatus errorStatus){
        if(errorStatus != null){
            return puffLocaleMessageSourceHolder.getMessage(errorStatus.getMsg());
        }
        return "";
    }

}
