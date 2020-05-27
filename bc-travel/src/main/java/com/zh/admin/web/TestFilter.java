package com.zh.admin.web;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
//@WebFilter注解方式配置
//默认拦截路径和拦截方式
//REQUEST：仅拦截浏览器直接发起的请求
//FORWARD：拦截服务器内部转发
//filterChain的执行顺序就只有比filter类名的字符串了
@WebFilter(value = "",dispatcherTypes = DispatcherType.REQUEST)

//@Component配置类方式配置，这样可以在配置类中配置过滤器链的执行顺序
//@Component

public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.getRequestDispatcher("/login");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
