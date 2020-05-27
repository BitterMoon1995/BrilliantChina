package com.zh.admin.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //定制授权规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/authorization/**").hasRole("1")
                .antMatchers("/client/**").hasRole("2")
                .antMatchers("/sticky*/**").hasRole("2")
                .antMatchers("/homepage/**").hasRole("2");
        //开启登陆功能，拦截一切请求到登录页
        http.formLogin()
        .and().authorizeRequests().anyRequest().authenticated();
        //开启自动注销功能    默认访问/logout 退出注销
        http.logout();
    }
    //认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }
}
