package com.hong.test.annotation;

import java.lang.annotation.*;

/**
 * 水果名称注解
 *
 * Created by Hongwei on 2015/10/25.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String value() default "";
}
