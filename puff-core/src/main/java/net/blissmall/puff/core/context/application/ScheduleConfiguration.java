package net.blissmall.puff.core.context.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;

/**
 * TODO 自定义异步执行计划和调度
 * @Author : zhuzhenglin
 * @Date : 16/8/9 22:03
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
//@Configuration
public class ScheduleConfiguration implements AsyncConfigurer,SchedulingConfigurer{

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Executor getAsyncExecutor() {
        return null;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

    }
}
