package com.hong.test.jvm.chapter7;

/**
 * Created by caihongwei on 21/11/2017 9:31 AM.
 */
public class SubClass extends SuperClass implements SuperInterface {
    static {
        System.out.println("Initialize sub class.");
    }
}
