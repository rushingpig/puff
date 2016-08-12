package net.blissmall.puff.core.persistence.interceptor;

import net.blissmall.puff.common.utils.ReflectionUtils;
import net.blissmall.puff.core.persistence.dialect.MySQLDialect;
import net.blissmall.puff.core.persistence.paging.Pagination;
import net.blissmall.puff.core.persistence.dialect.Dialect;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;

import java.io.Serializable;
import java.util.Properties;

/**
 * Mybatis分页拦截器基类
 * @author pigo
 * @version
 */
public abstract class BaseInterceptor implements Interceptor, Serializable {

    private static final long serialVersionUID = 1L;

    protected static final String PAGE = "page";

    protected static final String DELEGATE = "delegate";

    protected static final String MAPPED_STATEMENT = "mappedStatement";

    protected Log log = LogFactory.getLog(this.getClass());

    protected Dialect DIALECT;


    /**
     * 对参数进行转换和检查
     * @param parameterObject 参数对象
     * @param page            分页对象
     * @return 分页对象
     * @throws NoSuchFieldException 无法找到参数
     */
    @SuppressWarnings("unchecked")
    protected static Pagination<Object> convertParameter(Object parameterObject, Pagination<Object> page) {
        try{
            if (parameterObject instanceof Pagination) {
                return (Pagination<Object>) parameterObject;
            } else {
                return (Pagination<Object>) ReflectionUtils.getFieldValue(parameterObject, PAGE);
            }
        }catch (Exception e) {
            return null;
        }
    }

    /**
     * 设置属性，支持自定义方言类和制定数据库的方式
     * <code>dialectClass</code>,自定义方言类。可以不配置这项
     * <ode>dbms</ode> 数据库类型，插件支持的数据库
     * <code>sqlPattern</code> 需要拦截的SQL ID
     * @param p 属性
     */
    protected void initProperties(Properties p) {
        Dialect dialect = new MySQLDialect();
        DIALECT = dialect;
    }
}
