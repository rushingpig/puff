package net.blissmall.puff.core.shiro;

import com.google.common.collect.Sets;
import net.blissmall.puff.common.utils.DateUtils;
import net.blissmall.puff.common.utils.ServletUtils;
import net.blissmall.puff.common.utils.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * shiro session的dao
 * @Author : pigo
 * @Date : 16/4/16 下午10:50
 * @E-mail : zhenglin.zhu@xfxb.net
 */
public class CacheSessionDao extends EnterpriseCacheSessionDAO implements SessionDao {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public CacheSessionDao() {
        super();
    }

    @Override
    protected void doUpdate(Session session) {
//        if (session == null || session.getId() == null) {
//            return;
//        }
//
//        HttpServletRequest request = ServletUtils.getRequest();
//        if (request != null){
//            String uri = request.getServletPath();
//            // 如果是静态文件，则不更新SESSION
//            if (ServletUtils.isStaticFile(uri)){
//                return;
//            }
//            // 如果是视图文件，则不更新SESSION
//            if (StringUtils.startsWith(uri, GlobalUtils.getConfig("webcontext.view.prefix"))
//                    && StringUtils.endsWith(uri, GlobalUtils.getConfig("webcontext.view.suffix"))){
//                return;
//            }
//            // 手动控制不更新SESSION
//            String updateSession = request.getParameter("updateSession");
//            if (GlobalUtils.FALSE.equals(updateSession) || GlobalUtils.NO.equals(updateSession)){
//                return;
//            }
//        }
//        super.doUpdate(session);
//        logger.debug("update {} {}", session.getId(), request != null ? request.getRequestURI() : "");
    }

    @Override
    protected void doDelete(Session session) {
        if (session == null || session.getId() == null) {
            return;
        }

        super.doDelete(session);
        logger.debug("delete {} ", session.getId());
    }

    @Override
    protected Serializable doCreate(Session session) {
        HttpServletRequest request = ServletUtils.getRequest();
        if (request != null){
            String uri = request.getServletPath();
            // 如果是静态文件，则不创建SESSION
            if (ServletUtils.isStaticFile(uri)){
                return null;
            }
        }
        super.doCreate(session);
        logger.debug("doCreate {} {}", session, request != null ? request.getRequestURI() : "");
        return session.getId();
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return super.doReadSession(sessionId);
    }

    @Override
    public Session readSession(Serializable sessionId) throws UnknownSessionException {
        try{
            Session s = null;
            HttpServletRequest request = ServletUtils.getRequest();
            if (request != null){
                String uri = request.getServletPath();
                // 如果是静态文件，则不获取SESSION
                if (ServletUtils.isStaticFile(uri)){
                    return null;
                }
                s = (Session)request.getAttribute("session_"+sessionId);
            }
            if (s != null){
                return s;
            }

            Session session = super.readSession(sessionId);
            logger.debug("readSession {} {}", sessionId, request != null ? request.getRequestURI() : "");

            if (request != null && session != null){
                request.setAttribute("session_"+sessionId, session);
            }

            return session;
        }catch (UnknownSessionException e) {
            return null;
        }
    }

    /**
     * 获取活动会话
     * @param includeLeave 是否包括离线（最后访问时间大于3分钟为离线会话）
     * @return
     */
    @Override
    public Collection<Session> getActiveSessions(boolean includeLeave) {
        return getActiveSessions(includeLeave, null, null);
    }

    /**
     * 获取活动会话
     * @param includeLeave 是否包括离线（最后访问时间大于3分钟为离线会话）
     * @param principal 根据登录者对象获取活动会话
     * @param filterSession 不为空，则过滤掉（不包含）这个会话。
     * @return
     */
    @Override
    public Collection<Session> getActiveSessions(boolean includeLeave, Object principal, Session filterSession) {
        // 如果包括离线，并无登录者条件。
        if (includeLeave && principal == null){
            return getActiveSessions();
        }
        Set<Session> sessions = Sets.newHashSet();
        for (Session session : getActiveSessions()){
            boolean isActiveSession = false;
            // 不包括离线并符合最后访问时间小于等于3分钟条件。
            if (includeLeave || DateUtils.pastMinutes(session.getLastAccessTime()) <= 3){
                isActiveSession = true;
            }
            // 符合登陆者条件。
            if (principal != null){
                PrincipalCollection pc = (PrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                if (principal.toString().equals(pc != null ? pc.getPrimaryPrincipal().toString() : StringUtils.EMPTY)){
                    isActiveSession = true;
                }
            }
            // 过滤掉的SESSION
            if (filterSession != null && filterSession.getId().equals(session.getId())){
                isActiveSession = false;
            }
            if (isActiveSession){
                sessions.add(session);
            }
        }
        return sessions;
    }
}
