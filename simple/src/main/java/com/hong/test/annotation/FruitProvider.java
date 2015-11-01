package com.hong.test.annotation;

import java.lang.annotation.*;

/**
 * 水果供应者注解
 *
 * Created by Hongwei on 2015/10/25.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FruitProvider {
    /**
     * 供应商编码
     * @return
     */
    int id() default -1;

    /**
     * 供应商名称
     * @return
     */
    String name() default "";

    /**
     * 供应商地址
     * @return
     */
    String address() default "";
}
