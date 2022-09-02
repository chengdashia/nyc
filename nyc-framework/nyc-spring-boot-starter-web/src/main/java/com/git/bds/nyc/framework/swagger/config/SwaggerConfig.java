package com.git.bds.nyc.framework.swagger.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author :成大事
 * @since :2022-06-04 12:36:15
 * EnableSwagger2   开启swagger2
 * EnableKnife4j    该注解是knife4j提供的增强注解,Ui提供了例如动态参数、参数过滤、接口排序等增强功能,如果你想使用这些增强功能就必须加该注解，否则可以不用加
 */
@Configuration
@EnableKnife4j
@RequiredArgsConstructor
//@ConditionalOnProperty(name = "knife4j.enable", havingValue = "true", matchIfMissing = true)
public class SwaggerConfig {

    private final SwaggerProperties swaggerProperties;

    ///**
    // * api的主页显示信息
    // */
    //private static final ApiInfo API_INFO;

    /**
     * swagger激活环境
     */
    @Value(value = "${knife4j.enable}")
    public boolean enable;
    //
    //static {
    //    //API_INFO = new ApiInfoBuilder()
    //    //        .title("农营C 农作物交易平台")
    //    //        .description("农营C API接口文档")
    //    //        .termsOfServiceUrl("https://blog.chengdashi.cn")
    //    //        .contact(new Contact("成大事",
    //    //                "https://blog.chengdashi.cn",
    //    //                "1847085602@qq.com"))
    //    //        .version("1.0")
    //    //        .build();
    //}

    private ApiInfo info(){
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .contact(new Contact(swaggerProperties.getContact().getName()
                        ,swaggerProperties.getContact().getUrl()
                        ,swaggerProperties.getContact().getEmail()))
                .version(swaggerProperties.getVersion())
                .build();
    }


    /** 配置swagger的Docker的bean实例 */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api1")
                .apiInfo(info())
                .enable(enable)
//                .enable(flag)   //enable 是否启动swagger，如果为false，则swagger不能浏览器中访问
                .select()
                //RequestHandlerSelectors  配置要扫描接口的方式
                //basePackage :指定的包
                //any() :扫描全部
                //none :都不扫描
                //withClassAnnotation :扫描类上的注解  参数是一个注解的反射对象
                //withMethodAnnotation :扫描类上的注解
                .apis(RequestHandlerSelectors.basePackage("com.git.bds.nyc.controller"))
                //path()  过滤什么路径
                .paths(PathSelectors.any())
                .build();
    }


    /** 如果要新增一个分组：api2 */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 配置分组名
                .groupName("api2")
                .apiInfo(info())
                .enable(enable)
                .select()
                // 设置扫描包的地址 : com.hanliy.controller2
                .apis(RequestHandlerSelectors.basePackage("com.git.bds.nyc.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    /**
     * 解决高版本的springboot的匹配路径和spring-fox的不匹配
     */
    @Bean
    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return new BeanPostProcessor() {

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }

            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                List<T> copy = mappings.stream()
                        .filter(mapping -> mapping.getPatternParser() == null)
                        .collect(Collectors.toList());
                mappings.clear();
                mappings.addAll(copy);
            }

            @SuppressWarnings("unchecked")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
                try {
                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                    if (field != null) {
                        field.setAccessible(true);
                    }
                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }


}