package com.zh.admin.config;

import com.zh.admin.web.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@ComponentScan("com.zh.mini.controller")
@Configuration
@EnableSwagger2
/*
不管是实现WebMvcConfigurer，还是继承WebMvcConfigurationSupport
系统都只能有一个配置类，来进行springMVC的配置，所以建议在该配置类进行web层的一揽子配置
否则如果有多个MVC配置类，只会生效一个，其他的将会作废
2020/5/29 卡一上午
 */
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zh.mini.controller"))
                .build();
    }
    private ApiInfo apiInfo(){
        Contact contact = new Contact("", "", "");
        return new ApiInfo("最美绵州Api接口文档","","1.0",
                "http://118.24.106.117:8080/",contact,"","",new ArrayList<>());
    }

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**")
                //有坑
                .excludePathPatterns("/**/user/login**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //添加映射路径
        registry.addMapping("/**")
                //放行哪些原始域
                .allowedOrigins("*")
                //是否发送Cookie信息
                .allowCredentials(true)
                //放行哪些原始域(请求方式)
                .allowedMethods("GET","POST", "PUT", "DELETE")
                //放行哪些原始域(头部信息)
                .allowedHeaders("*")
                //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
                .exposedHeaders("Header1", "Header2");
    }
}
