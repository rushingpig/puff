package net.blissmall.puff.core.annotation;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.lang.annotation.*;

/**
 * 自定义启动注解类
 * TODO 根据框架需求,完善该类的注解方法实现
 * @Author : zhuzhenglin
 * @Date : 16/8/9 10:41
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class))
public @interface PuffBootApplication {
}
