package net.blissmall.puff.core.context.application;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

/**
 * 不要跟{@link ShiroConfiguration} 放在同一个bean里
 * <li>
 *     因为{@link LifecycleBeanPostProcessor}注入后,接下来的所有bean都会初始化完成,然后才会执行{@link org.springframework.beans.factory.annotation.Autowired}Autowired 和 {@link javax.annotation.Resource}
 * </li>
 * @Author : pigo
 * @Date : 16/4/21 下午6:17
 * @E-mail : zhenglin.zhu@xfxb.net
 */
//@Configuration
public class ShiroLifecycleConfiguration {

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
}
