package net.blissmall.puff.core.annotation;

import org.springframework.context.annotation.Profile;

import java.lang.annotation.*;

/**
 * 用户开发环境的注解
 * @Author : zhuzhenglin
 * @Date : 16/8/9 21:25
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Profile("qa")
public @interface Qa {
}
