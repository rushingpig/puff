package net.blissmall.puff.web.core.Aware;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 自定义环境变量的读取和属性值的绑定
 * @Author : zhuzhenglin
 * @Date : 16/8/3 23:42
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Configuration
public class PuffEnvironmentAware implements EnvironmentAware {
    /**
     * 该方法在系统启动的时候执行
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        //获取到前缀是"spring.datasource." 的属性列表值.
        RelaxedPropertyResolver relaxedPropertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
    }
}
