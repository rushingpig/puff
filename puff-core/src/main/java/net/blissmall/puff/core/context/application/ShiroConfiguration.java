package net.blissmall.puff.core.context.application;

import net.blissmall.puff.core.shiro.*;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : pigo
 * @Date : 16/4/16 下午4:31
 * @E-mail : zhenglin.zhu@xfxb.net
 */
//@Configuration

public class ShiroConfiguration {

    private final String cookieName = "puff.session.id";

    @Autowired
    private ShiroProperties shiroProperties;
    @Resource(name = "formAuthenticationFilter")
    private Filter formAuthenticationFilter;
    @Autowired
    private IdGenUtils idGenUtils;

    private Map<String,Filter> filters(){
        Map<String,Filter> map = new HashMap<>();
        map.put("authc",formAuthenticationFilter);
        return map;
    }
//
//    /**
//     * 获取casFilter实例对象,继承cas功能
//     * @return
//     */
//    @Bean
//    public CasFilter casFilter(){
//        CasFilter casFilter = new CasFilter();
//        casFilter.setName("casFilter");
//        casFilter.setEnabled(true);
//        // 设置登录失败后的跳转URL
//        casFilter.setFailureUrl("");
//        return casFilter;
//    }

    /**
     * 获取shiro filter 的FactoryBean实例
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(defaultWebSecurityManager());
        shiroFilter.setLoginUrl(shiroProperties.getLoginUrl());
        shiroFilter.setSuccessUrl(shiroProperties.getSuccessUrl());
        shiroFilter.setFilters(filters());
        shiroFilter.setFilterChainDefinitions(shiroProperties.getFilterChainDefinitions());
        return shiroFilter;
    }


    @Bean
    public SimpleCookie simpleCookie(){
        return new SimpleCookie(cookieName);
    }

    /**
     * shiro内部默认的安全管理
     * @return
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(new SystemAuthorizingRealm());
        manager.setSessionManager(sessionManager());
        manager.setCacheManager(shiroEhCacheManager());
        return manager;
    }

    /**
     * 使用ehcache作为shiro内部数据的缓存方式
     * @return
     */
    @Bean
    @ConditionalOnBean(ShiroProperties.class)
    public CacheManager cacheManager(){
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        ehCacheManagerFactoryBean.setConfigLocation(resourceResolver.getResource(shiroProperties.getEhcacheConfigLocation()));
        return ehCacheManagerFactoryBean.getObject();
    }

    @Bean
    @ConditionalOnBean(name = "cacheManager")
    public EhCacheManager shiroEhCacheManager(){
        EhCacheManager manager = new EhCacheManager();
        manager.setCacheManager(cacheManager());
        return manager;
    }

    /**
     * 获取shiro内部的SessionDao对象实例
     * @return
     */
    @Bean(name = "sessionDao")
    @ConditionalOnBean(name = "shiroEhCacheManager")
    public CacheSessionDao cacheSessionDao(){
        CacheSessionDao cacheSessionDao = new CacheSessionDao();
        cacheSessionDao.setSessionIdGenerator(idGenUtils);
        cacheSessionDao.setActiveSessionsCacheName("activeSessionsCache");
        cacheSessionDao.setCacheManager(shiroEhCacheManager());
        return cacheSessionDao;
    }

    /**
     * 获取shiro内部的session管理实例
     * @return
     */
    @Bean
    public SessionManager sessionManager(){
        SessionManager sessionManager = new SessionManager();
        sessionManager.setSessionDAO(cacheSessionDao());
        sessionManager.setGlobalSessionTimeout(shiroProperties.getGlobalSessionTimeout());
        sessionManager.setSessionValidationInterval(shiroProperties.getSessionValidationInterval());
        sessionManager.setSessionValidationSchedulerEnabled(shiroProperties.isSessionValidationSchedulerEnabled());
        sessionManager.setSessionIdCookie(simpleCookie());
        sessionManager.setSessionIdCookieEnabled(true);
        return sessionManager;
    }
}
