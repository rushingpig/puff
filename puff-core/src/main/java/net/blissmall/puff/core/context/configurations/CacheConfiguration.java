package net.blissmall.puff.core.context.configurations;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : zhuzhenglin
 * @Date : 16/9/8 22:39
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Configuration
@EnableCaching(proxyTargetClass = true)
public class CacheConfiguration extends CachingConfigurerSupport {

    @Value("${ehcache-config-location}")
    private String ehcacheConfigLocation;

    @Resource
    private RedisConnectionFactory factory;


    @Bean
    public CompositeCacheManager cacheManager() {
        CompositeCacheManager compositeCacheManager = new CompositeCacheManager();
        List<CacheManager> cacheManagers = Lists.newArrayList();
        cacheManagers.add(redisCacheManager());
        cacheManagers.add(ehCacheCacheManager());
        compositeCacheManager.setFallbackToNoOpCache(true);
        compositeCacheManager.setCacheManagers(cacheManagers);
        return compositeCacheManager;
    }

    @Bean
    public CacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
        return redisCacheManager;
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager(){
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        ehCacheManagerFactoryBean.setConfigLocation(resourceResolver.getResource(ehcacheConfigLocation));
        ehCacheManagerFactoryBean.setShared(true);
        return new EhCacheCacheManager(ehCacheManagerFactoryBean.getObject());
    }

    @Bean
    public KeyGenerator puffKeyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(".");
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
