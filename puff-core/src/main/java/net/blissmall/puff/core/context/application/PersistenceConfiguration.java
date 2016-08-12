package net.blissmall.puff.core.context.application;

import com.github.pagehelper.PageHelper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.blissmall.puff.core.persistence.properties.HikariProperties;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @Des : 所有跟持久层相关configuration配置
 * @Author : pigo
 * @Date : 16/4/14 下午9:06
 * @E-mail : zhenglin.zhu@xfxb.net
 */
@Configuration
@EnableTransactionManagement
@ConfigurationProperties(prefix = "mybatis", ignoreInvalidFields = true, locations = {"classpath:properties/puff.yml"})
//@MapperScan(basePackages = "net.xfxb.puff.app.module.mapper",sqlSessionFactoryRef = "sqlSessionFactory",annotationClass = MyBatisDao.class)
public class PersistenceConfiguration {

    private final Logger logger = LoggerFactory.getLogger(PersistenceConfiguration.class);

    private String typeAliasPackage;
    private String mapperLocations;
    private String configLocation;

    @Autowired
    private HikariProperties hikariProperties;
//    @Autowired
//    private Environment env;

    @Bean(destroyMethod = "close")
    public DataSource dataSource(){
        if(logger.isDebugEnabled()){
            logger.debug("===  config the datasource... ===");
        }
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(hikariProperties.getJdbcUrl());
        config.setUsername(hikariProperties.getUsername());
        config.setPassword(hikariProperties.getPassword());
        config.setAutoCommit(hikariProperties.isAutoCommit());
        config.setConnectionTimeout(hikariProperties.getConnectionTimeout());
        config.setIdleTimeout(hikariProperties.getIdleTimeout());
        config.setMaxLifetime(hikariProperties.getMaxLifetime());
        config.setMinimumIdle(hikariProperties.getMinimumIdle());
        config.setMaximumPoolSize(hikariProperties.getMaximumPoolSize());
        config.setPoolName(hikariProperties.getPoolName());
        config.setValidationTimeout(hikariProperties.getValidationTimeout());

        config.addDataSourceProperty("cachePrepStmts", hikariProperties.getCachePrepStmts());
        config.addDataSourceProperty("prepStmtCacheSize", hikariProperties.getPrepStmtCacheSize());
        config.addDataSourceProperty("prepStmtCacheSqlLimit", hikariProperties.getPrepStmtCacheSqlLimit());
        return new HikariDataSource(config);

    }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

    /**
     *
     * @param dataSource 如果有DataSource的实例,将会被自动注入
     * @return sqlSessionFactory实例
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage(typeAliasPackage);

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        sessionFactory.setPlugins(new Interceptor[]{pageHelper});

        PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resourceResolver.getResources(mapperLocations));
        sessionFactory.setConfigLocation(resourceResolver.getResource(configLocation));
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    public String getTypeAliasPackage() {
        return typeAliasPackage;
    }

    public void setTypeAliasPackage(String typeAliasPackage) {
        this.typeAliasPackage = typeAliasPackage;
    }

    public String getMapperLocations() {
        return mapperLocations;
    }

    public void setMapperLocations(String mapperLocations) {
        this.mapperLocations = mapperLocations;
    }

    public String getConfigLocation() {
        return configLocation;
    }

    public void setConfigLocation(String configLocation) {
        this.configLocation = configLocation;
    }
}
