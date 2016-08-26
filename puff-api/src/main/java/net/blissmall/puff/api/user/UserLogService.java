package net.blissmall.puff.api.user;

import net.blissmall.puff.vo.user.LoginVo;

import java.util.concurrent.Future;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/19 01:14
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public interface UserLogService {
    /**
     * 记录用户登录日志
     * @param loginVo
     */
    Future<Integer> recordLoginLog(LoginVo loginVo, String uuid);
}
