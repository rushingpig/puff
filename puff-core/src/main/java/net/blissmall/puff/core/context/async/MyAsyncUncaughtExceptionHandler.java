package net.blissmall.puff.core.context.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * 处理异步任务抛出的异常
 */
public class MyAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        logger.error("Method Name::"+method.getName());
        logger.error("Exception occurred::"+ ex);
    }
} 