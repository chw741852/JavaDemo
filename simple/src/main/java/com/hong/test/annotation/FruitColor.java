package com.hong.test.annotation;

import java.lang.annotation.*;

/**
 * 水果颜色注解
 *
 * Created by Hongwei on 2015/10/25.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FruitColor {
    /**
     * 颜色枚举
     */
    enum Color {BLLE, RED, GREEN};

    /**
     * 颜色属性
     * @return
     */
    Color fruitColor() default Color.GREEN;
}
