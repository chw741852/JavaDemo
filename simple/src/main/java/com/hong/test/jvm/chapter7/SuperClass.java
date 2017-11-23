package com.hong.test.jvm.chapter7;

/**
 * Created by caihongwei on 21/11/2017 9:30 AM.
 */
public class SuperClass {
    static {
        System.out.println("Initialize super class.");
    }

    protected static int v = 10;
}
