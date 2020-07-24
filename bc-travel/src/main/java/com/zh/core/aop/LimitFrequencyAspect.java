package com.zh.core.aop;


import com.baomidou.mybatisplus.extension.api.R;
import com.zh.core.constant.AppContext;
import com.zh.core.utils.HttpsUtils;
import com.zh.core.utils.IPUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


@Component
@Aspect
@Order(1)
public class LimitFrequencyAspect {

    private static final String REQ_LIMIT = "req_limit_";

    @Autowired
    StringRedisTemplate stringTemplate;

    /* 小解释    *:任意返回值
    com.zh.admin.controller:固定包
    *(..):所有方法，任意参数
     */
    @Around(
//            "execution( * com.zh.admin.controller*(..) )"+
//            "||execution( * com.zh.mini.controller*(..) )"+
            "@annotation(com.zh.core.aop.LimitFrequency)"
    )

    //ProceedingJoinPoint是环绕通知必备的连接点类
    public Object limit(ProceedingJoinPoint pjp) throws Throwable {
        //通过连接点获取方法签名
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        //通过方法签名(包括方法体和返回值)获取方法体
        Method target = signature.getMethod();

        //初始化限制条件
        LimitFrequency anno = target.getAnnotation(LimitFrequency.class);
        int time;int limit;int block;
        if (anno==null) {
            time = 5;limit = 30;block = 300;
        }
        assert anno != null;
        time = anno.time();limit = anno.limit();block = anno.block();

        //生成Redis要存的のKey
        HttpServletRequest request = HttpsUtils.getRequest();
        String ipAddr = IPUtils.getIpAddr(request);
        String url = request.getRequestURI();
        String key = requestLimitKey(url,ipAddr);

        /*
        核心思路：
        键值对的key已由IP+url确定，键值对的值就是目标方法调用的次数，而过期时间就是设置的time。
        每调用一次，次数+1，如果达到次数上限count，拒绝调用
         */
        ValueOperations<String, String> redis = stringTemplate.opsForValue();
        //转码出现问题，“1”转不了1
        String value = redis.get(key);
        /*
        一个极小的细节（卡3小时）：
        ”（代表空格）”这个符号在idea中是即不显示又不占用空间的，
        而在这里从Redis中取出来的value”1“是个64进制的值，最后一位是1，有61个0，还有两个不晓得
        必须使用trim去掉所有的0。说白了还是序列化的问题
         */
        int count = redis.get(key) == null ? 0 : Integer.parseInt(value.trim());
        //第一次ping该接口，初始化  URL+IP：限制时间   键，并放行
        if (count == 0) {
            count++;
            redis.set(key, String.valueOf(count),time,TimeUnit.SECONDS);
            return pjp.proceed();
        }
        //能进else，说明是在限制时间内，第N次ping该接口
        else {
            //N自增，并同步到Redis中
            count++;
            /*
            小Api:   void set(K var1, V var2, long var3)  表示覆盖从指定位置开始的值,
            var3方法表示偏移量。通过该方法并将var3设置为0可以修改值而不影响键的过期时间
             */
            redis.set(key,String.valueOf(count),0);
            //如果限制时间内，N超过限制次数limit
            if (count >= limit){
                //为该URL+IP组合的键设置过期时间，过期时间为周神规定的block秒
                stringTemplate.expire(key,block, TimeUnit.SECONDS);

                //很明显在block秒内，该键一直存在，且限制条件N>limit始终成立，
                //这时只要不予放行，那么只要block秒没走完，该IP就无法调用该接口
                return returnLimit(request);
            }
        }

        return pjp.proceed();
    }

    private static String requestLimitKey(String url, String ip) {

        return REQ_LIMIT +
                url +
                "_" +
                ip;
    }

    private String returnLimit(HttpServletRequest request) throws IOException {

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getResponse();
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        out.println("{\"code\":" + AppContext.CODE_50004
                + ",\"msg\":\"Service reject request!\"}");
        out.flush();
        out.close();
        return null;

    }
}
