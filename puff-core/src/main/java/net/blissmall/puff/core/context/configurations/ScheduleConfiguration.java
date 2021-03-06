package net.blissmall.puff.core.context.configurations;

import net.blissmall.puff.core.context.async.MyAsyncUncaughtExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * // TODO 自定义异步执行计划和调度
 * @Author : zhuzhenglin
 * @Date : 16/8/9 22:03
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@Configuration
@EnableAsync
public class ScheduleConfiguration implements AsyncConfigurer{

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(10);
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncUncaughtExceptionHandler();
    }
}
