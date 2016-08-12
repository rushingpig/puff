package net.blissmall.puff.web.core.webcontext;

import net.blissmall.puff.web.core.handler.PuffResponseErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Author : pigo
 * @Date : 5/9/16 16:42
 * @E-mail : zhenglin.zhu@xfxb.net
 */
@Configuration
@ConfigurationProperties(prefix = "rest", ignoreInvalidFields = true, exceptionIfInvalid = false,locations = {"classpath:properties/puff.yml"})
public class RestHttpConfiguration {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private int connectTimeout;
    private int readTimeout;

    /**
     * 获取RestTemplate实例,用户发起restful客户端请求<br/>
     * 只在session范围内有效,避免多个不同session的RestTemplate实例混淆
     *
     * @return {@link RestTemplate}
     */
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE,proxyMode = ScopedProxyMode.DEFAULT)
    public RestTemplate restTemplate() {
//        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(connectTimeout);
        clientHttpRequestFactory.setReadTimeout(readTimeout);
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        restTemplate.setErrorHandler(new PuffResponseErrorHandler());
        restTemplate.setMessageConverters(SpringMVCConfiguration.wrapMessageConverter());
        return restTemplate;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }
}
