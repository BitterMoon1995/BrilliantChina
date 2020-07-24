package com.zh.core.config;

import com.zh.core.web.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
        registry.addInterceptor(authInterceptor)
                //增、删、字段修改、后台菜单列表
                .addPathPatterns("/add","/del","/change","/menu/getAll")
                //有坑
                .excludePathPatterns("/login");
    }
}
