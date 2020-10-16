package com.zh.core.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@SpringBootConfiguration
//@ImportResource({"classpath:c3p0.properties"})
public class DBConfig {

    @Bean(name = "dataSource")
    @Qualifier("dataSource")//指定注入
    @Primary//提高【政治地位】
    @ConfigurationProperties(prefix = "c3p0")
    public DataSource c3p0(){
        return DataSourceBuilder.create().type(ComboPooledDataSource.class)
                .build();
    }

}
