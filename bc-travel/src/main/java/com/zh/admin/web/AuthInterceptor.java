package com.zh.admin.web;

import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader("Authorization");
        String account = request.getHeader("Account");
        ValueOperations<String, String> stringOption = redisTemplate.opsForValue();

        String serverAuth = stringOption.get(account);
        assert serverAuth != null;
        //根据 用户名-token 键值对的匹配来对所以请求（除登陆）做安全判断，不匹配返false。、
        //暂时做到这个程度。不匹配重定向到登陆页面没搞出来(重定向过不了CORS)
        return serverAuth.equals(auth);
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
