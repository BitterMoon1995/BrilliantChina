package com.zh.core.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//限定该注解的使用位置:方法
@Target(ElementType.METHOD)
//想要被反射读取，只能
@Retention(RetentionPolicy.RUNTIME)


//限制晶哥请求频率
//time秒内请求次数达到limit次，屏蔽block秒
public @interface LimitFrequency {

    int time() default 5;

    int limit() default 20;

    int block() default 300;
}
