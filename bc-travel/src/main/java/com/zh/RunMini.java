package com.zh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.TimeZone;

@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = {"com.zh.mini.mapper","com.zh.admin.mapper"})
public class RunMini {
    public static void main(String[] args) {
        SpringApplication.run(RunMini.class,args);
    }
}
