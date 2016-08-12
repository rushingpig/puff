package net.blissmall.puff.core.shiro;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;

import java.util.Collection;

/**
 * @Author : pigo
 * @Date : 16/4/16 下午10:49
 * @E-mail : zhenglin.zhu@xfxb.net
 */
public interface SessionDao extends SessionDAO {

    /**
     * 获取活动会话
     * @param includeLeave 是否包括离线（最后访问时间大于3分钟为离线会话）
     * @return
     */
    public Collection<Session> getActiveSessions(boolean includeLeave);

    /**
     * 获取活动会话
     * @param includeLeave 是否包括离线（最后访问时间大于3分钟为离线会话）
     * @param principal 根据登录者对象获取活动会话
     * @param filterSession 不为空，则过滤掉（不包含）这个会话。
     * @return
     */
    public Collection<Session> getActiveSessions(boolean includeLeave, Object principal, Session filterSession);
}
