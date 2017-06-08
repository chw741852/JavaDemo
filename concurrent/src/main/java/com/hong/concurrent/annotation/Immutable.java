package com.hong.concurrent.annotation;

import java.lang.annotation.*;

/**
 * Created by caihongwei on 07/06/2017 7:41 PM.
 *
 * 不可变得
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Immutable {
}
