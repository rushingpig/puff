package net.blissmall.puff.web.j2ee;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 通过监听器在容器启动的时候获取spring的上下文
 *
 * @Author : zhuzhenglin
 * @Date : 16/8/4 00:29
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@WebListener
public class SpringContextLoaderListener implements ServletContextListener {
    private static ApplicationContext springContext = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if (springContext == null) {
            SpringContextLoaderListener.springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    public static Object getBean(String beanName) {
        return springContext.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> clazz) {
        return springContext.getBean(beanName, clazz);
    }

}
