package net.blissmall.puff.web.controller;

import net.blissmall.puff.web.core.holder.PuffLocaleMessageSourceHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/10 22:32
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    protected PuffLocaleMessageSourceHolder messageSourceHolder;
}
