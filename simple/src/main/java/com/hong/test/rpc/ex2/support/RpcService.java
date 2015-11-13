package com.hong.test.rpc.ex2.support;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 简单的RPC协议方法注解
 * Created by Hongwei on 2015/11/12.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RpcService {
    String value() default "";

    Class<?> inf();
}
