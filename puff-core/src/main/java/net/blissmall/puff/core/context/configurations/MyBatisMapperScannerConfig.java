package net.blissmall.puff.core.context.configurations;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * MyBatis扫描接口，使用的tk.mybatis.spring.mapper.MapperScannerConfigurer，如果你不使用通用Mapper，可以改为org.xxx...<br/>
 * <b>注意，由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解{@link AutoConfigureAfter}</b>
 * @author liuzh
 * @since 2015-12-19 14:46
 */
@Configuration
@AutoConfigureAfter(PersistenceConfiguration.class)
public class MyBatisMapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("net.blissmall.puff.service.mapper");
//        mapperScannerConfigurer.setAnnotationClass(MyBatisDao.class);
        Properties properties = new Properties();
        properties.setProperty("mappers", "net.blissmall.puff.core.mapper.MyMapper");
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}