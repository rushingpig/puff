package net.blissmall.puff.core.shiro;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author : pigo
 * @Date : 16/4/16 下午4:58
 * @E-mail : zhenglin.zhu@xfxb.net
 */
@Component
@ConfigurationProperties(prefix = "shiro", locations = {"classpath:properties/puff.yml"})
public class  ShiroProperties {

    private String loginUrl;
    private String successUrl;
    private String filterChainDefinitions;

    private String ehcacheConfigLocation;
    private long globalSessionTimeout;
    private long sessionValidationInterval;
    private boolean sessionValidationSchedulerEnabled;

    public String getEhcacheConfigLocation() {
        return ehcacheConfigLocation;
    }

    public void setEhcacheConfigLocation(String ehcacheConfigLocation) {
        this.ehcacheConfigLocation = ehcacheConfigLocation;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }


    public String getFilterChainDefinitions() {
        return filterChainDefinitions;
    }

    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }

    public long getGlobalSessionTimeout() {
        return globalSessionTimeout;
    }

    public void setGlobalSessionTimeout(long globalSessionTimeout) {
        this.globalSessionTimeout = globalSessionTimeout;
    }

    public long getSessionValidationInterval() {
        return sessionValidationInterval;
    }

    public void setSessionValidationInterval(long sessionValidationInterval) {
        this.sessionValidationInterval = sessionValidationInterval;
    }

    public boolean isSessionValidationSchedulerEnabled() {
        return sessionValidationSchedulerEnabled;
    }

    public void setSessionValidationSchedulerEnabled(boolean sessionValidationSchedulerEnabled) {
        this.sessionValidationSchedulerEnabled = sessionValidationSchedulerEnabled;
    }
}
