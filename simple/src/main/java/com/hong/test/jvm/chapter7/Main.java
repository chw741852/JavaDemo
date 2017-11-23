package com.hong.test.jvm.chapter7;

/**
 * Created by caihongwei on 21/11/2017 9:32 AM.
 */
public class Main {
    public static void main(String[] args) {
        // 因为SubClass调用的v是在SuperClass中定义的，所以初始化了SuperClass；
        // 此处并未初始化SubClass.
        System.out.println(SubClass.v);
    }
}
