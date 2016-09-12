package net.blissmall.puff.web.core.webcontext;

import com.thoughtworks.xstream.io.xml.StaxDriver;
import net.blissmall.puff.common.utils.JSONUtils;
import net.blissmall.puff.web.core.view.PuffJsonViewResolver;
import net.blissmall.puff.web.interceptor.LoginInterceptor;
import net.blissmall.puff.web.interceptor.RegionInterceptor;
import org.apache.commons.io.Charsets;
import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @EnableWebMvc 注解, 会让org.springframework.boot.autoconfigure.webcontext.WebMvcAutoConfiguration失效
 * 很多东西要手动配置
 * @Author : pigo
 * @Date : 16/4/22 下午3:00
 * @E-mail : zhenglin.zhu@xfxb.net
 */
@Configuration
//@EnableWebMvc
@ConfigurationProperties(prefix = "i18n", ignoreInvalidFields = true, locations = {"classpath:properties/puff.yml"})
public class PuffMVCConfiguration extends WebMvcConfigurerAdapter implements EmbeddedServletContainerCustomizer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ResourceProperties resourceProperties = new ResourceProperties();

    @Resource
    private ApplicationContext applicationContext;

    private static final String STATIC_PATH_PATTERN = "/static/**";

    private String messageBaseName;
    private int cacheSeconds;


    // 实现跨域访问

   /* @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api*//**")
     .allowedOrigins("http://domain2.com")
     .allowedMethods("PUT", "DELETE")
     .allowedHeaders("header1", "header2", "header3")
     .exposedHeaders("header1", "header2")
     .allowCredentials(false).maxAge(3600);
     }*/

    /**
     * {@link WebMvcAutoConfiguration} 已经默认实现了该注入
     * @param registry
     */
    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/webjars*//**")) {
     registry.addResourceHandler("/webjars*//**").addResourceLocations("classpath:/META-INF/resources/webjars/");
     }
     if (!registry.hasMappingForPattern("*//**")) {
     registry.addResourceHandler("*/
    /**
     * ").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
     * }
     * }
     */

    /**
     * 扩展添加自定义的welcome-page的映射<br/>
     * 默认 / -> index.html <br/>
     * 当
     * <li>classpath:/META-INF/resources/</li>
     * <li>classpath:/resources/</li>
     * <li>classpath:/static/</li>
     * <li>classpath:/public/</li>
     * 下的index.html不存在时,映射到welcome.html
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:welcome.html");
    }

    /**
     * 扩展添加自定义的资源处理器
     * http://xxxxx/static/foo.js -> 对应/static/文件夹下的资源foo.js
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern(STATIC_PATH_PATTERN)) {
            registry.addResourceHandler(STATIC_PATH_PATTERN).addResourceLocations(this.resourceProperties.getStaticLocations());
        }
        super.addResourceHandlers(registry);
    }

    /**
     * <pre>
     * setUseSuffixPatternMatch : 设置是否是后缀模式匹配，如“/user”是否匹配/user.*，默认真即匹配；
     * setUseTrailingSlashMatch : 设置是否自动后缀路径模式匹配，如“/user”是否匹配“/user/”，默认真即匹配；
     * 项目中为了适应restful模式,设置后缀模式不匹配
     * </pre>
     * @param configurer
     */
//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        configurer.setUseSuffixPatternMatch(false).setUseTrailingSlashMatch(true);
//    }

    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    /**
     * 添加自定义的拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //1.请求参数的打印
//        registry.addInterceptor(new ParamsInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new RegionInterceptor()).addPathPatterns("/category/*", "/product/*", "/cart", "/cart/*");
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/user/**").excludePathPatterns("/user/exist","/user/login","/user/registry","/user/quickLogin","/user/preReset","/user/logout");
        super.addInterceptors(registry);
    }


    /**
     * @see #contentNegotiatingViewResolver(ContentNegotiationManager, ThymeleafViewResolver)
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.addAll(wrapMessageConverter());
        super.configureMessageConverters(converters);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true).
                favorParameter(false).
                ignoreAcceptHeader(true).
                useJaf(false).
                /*
                设置后会影响HttpMessageConverter
                defaultContentType(MediaType.TEXT_HTML).
                */
                mediaType(MediaType.TEXT_HTML_VALUE,MediaType.TEXT_HTML).
                mediaType(MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_XML).
                mediaType(MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON).
                mediaType(MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_UTF8);
    }

    /**
     * Configure ContentNegotiatingViewResolver
     * <pre>
     *     <b>说明下{@link ContentNegotiatingViewResolver}和{@link HttpMessageConverter}的区别</b>
     *     <li>当使用@ResponseBody注解时,http body消息会交给HttpMessageConverter处理</li>
     *     <li>反之,只交给内容协商视图处理</li>
     * </pre>
     */
    @Bean(name = "viewResolver")
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);

        // Define all possible view resolvers
        List<ViewResolver> resolvers = new ArrayList<>();
        resolvers.add(thymeleafViewResolver());
        resolvers.add(jsonViewResolver());
        resolver.setViewResolvers(resolvers);
        return resolver;
    }
    /*
     * Configure View resolver to provide JSON output using JACKSON library to
     * convert object in JSON format.
     */
    @Bean
    public ViewResolver jsonViewResolver() {
        return new PuffJsonViewResolver();
    }

    //  国际化配置

    /**
     * <pre>
     * springboot提供了默认的实现MessageSourceAutoConfiguration
     * </pre>
     * @return
     */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(messageBaseName);
        messageSource.setDefaultEncoding(Charsets.UTF_8.name());
        //  设置为TRUE后EL表达式可能会失效
        messageSource.setUseCodeAsDefaultMessage(false);
        messageSource.setCacheSeconds(cacheSeconds);
        return messageSource;
    }

    // validator配置
    @Bean
    @Primary
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setProviderClass(HibernateValidator.class);
        validator.setValidationMessageSource(messageSource());

        return validator;
    }

    /**
     * 支持方法级别的参数验证
     * @return
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(validator());
        return processor;
    }

    // 复写父类方法来自定义Validator
    @Override
    public Validator getValidator() {
        return validator();
    }

    public String getMessageBaseName() {
        return messageBaseName;
    }

    public void setMessageBaseName(String messageBaseName) {
        this.messageBaseName = messageBaseName;
    }

    public int getCacheSeconds() {
        return cacheSeconds;
    }

    public void setCacheSeconds(int cacheSeconds) {
        this.cacheSeconds = cacheSeconds;
    }

    /**
     * 自定义404 | 500 的容器页面处理
     * @param container
     */
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400.html"));
        container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
        container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html"));
//        container.setSessionTimeout(15, TimeUnit.SECONDS);
    }

    public static List<HttpMessageConverter<?>> wrapMessageConverter(){
        List<HttpMessageConverter<?>> messageConverterList = new ArrayList<>();

        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charsets.UTF_8);
        messageConverterList.add(stringHttpMessageConverter);
        /*
         * 配置json的httpMessageConverter
         */
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setPrettyPrint(true);
        mappingJackson2HttpMessageConverter.setObjectMapper(new JSONUtils());
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8, MediaType.APPLICATION_JSON));
        messageConverterList.add(mappingJackson2HttpMessageConverter);
        /*
         * 配置xml解析
         */
        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
        xStreamMarshaller.setStreamDriver(new StaxDriver());
        MarshallingHttpMessageConverter marshallingHttpMessageConverter = new MarshallingHttpMessageConverter(xStreamMarshaller);
        marshallingHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_XML));
        messageConverterList.add(new MarshallingHttpMessageConverter());

        return messageConverterList;
    }

    @Bean(name = "thymeleafViewResolver")
    public ViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        resolver.setContentType(MediaType.TEXT_HTML_VALUE);
        return resolver;
    }

    private TemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addTemplateResolver(urlTemplateResolver());
        engine.addTemplateResolver(templateResolver());
        // pre-initialize the template engine by getting the configuration.  It's a side-effect.
        engine.getConfiguration();
        return engine;
    }

    private ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("classpath:templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCacheable(false);
        return resolver;
    }

    private UrlTemplateResolver urlTemplateResolver() {
        return new UrlTemplateResolver();
    }
}
