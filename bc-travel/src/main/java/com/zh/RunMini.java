package com.zh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.TimeZone;
//开启基于注解的缓存
@EnableCaching
/*
@ServletComponentScan ：在SpringBootApplication上使用@ServletComponentScan注解后，
Servlet、Filter、Listener可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册，无需其他代码。
 */
@ServletComponentScan
@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = {"com.zh.mini.mapper","com.zh.admin.mapper"})
public class RunMini {
    public static void main(String[] args) {
        SpringApplication.run(RunMini.class,args);
    }
}
