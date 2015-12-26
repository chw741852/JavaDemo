package com.hong.test.simple;

/**
 * Created by Hongwei on 2015/12/26.
 * 验证静态块和构造方法的执行顺序
 */
public class B extends A {
    private static int b = 5;

    public B() {
        System.out.println("In B constructor");
    }

    static {
        System.out.println("In B static -- " + b);
    }

    public static void main(String[] args) {
        new B();
    }
}
