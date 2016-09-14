package net.blissmall.puff.web;

import net.blissmall.puff.common.utils.WebUtils;
import net.blissmall.puff.service.constant.PuffNamedConstant;
import net.blissmall.puff.vo.user.UserInfoVo;
import net.blissmall.puff.web.core.holder.PuffLocaleMessageSourceHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Author : zhuzhenglin
 * @Date : 16/9/14 15:55
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public class CommonController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    protected PuffLocaleMessageSourceHolder puffLocaleMessageSourceHolder;

    protected UserInfoVo getLoginUser(HttpSession session){
        return WebUtils.getSessionAttribute(session, PuffNamedConstant.USER_SESSION_KEY,UserInfoVo.class);
    }

    @ModelAttribute
    public String getUuid(HttpSession session){
        UserInfoVo sessionUser = getLoginUser(session);
        if(sessionUser == null || sessionUser.getAppUserAuths() == null){
            return "";
        }
        return sessionUser.getAppUserAuths().getUuid();
    }
}
