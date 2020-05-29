//package com.zh.admin.web;
//
//import org.apache.http.HttpStatus;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import static org.springframework.data.elasticsearch.annotations.DateFormat.time;
//
//@WebFilter(urlPatterns = "/**")
//public class CorsFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//
//        // 添加参数，允许任意domain访问
//        resp.setHeader("Access-Control-Allow-Origin", "*");
//        // 这个allow-headers要配为*，这样才能允许所有的请求头 --- update by zxy  in 2018-10-19
//        resp.setHeader("Access-Control-Allow-Headers", "*");
//        resp.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
//        resp.setHeader("Access-Control-Max-Age", time+"3600");
//
//        // 浏览器是会先发一次options请求，如果请求通过，则继续发送正式的post请求
//        // 配置options的请求返回
//        if (request.getMethod().equals("OPTIONS")) {
//            resp.setStatus(HttpStatus.SC_OK);
//            resp.getWriter().write("OPTIONS returns OK");
//            return;
//        }
//
//        filterChain.doFilter(servletRequest, resp);
//    }
//}
