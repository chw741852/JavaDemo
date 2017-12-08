package com.hong.test.simple;

/**
 * Created by caihongwei on 08/12/2017 8:16 PM.
 * 自动拆箱、装箱
 */
public class C {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Integer h = 128;
        Integer i = 193;
        Long g = 3L;
        System.out.println(c == d); // true 因为-127~128之间用的地址一样
        System.out.println(e == f); // false
        System.out.println(c == (a + b));   // true，加法之后自动拆箱
        System.out.println(f == (h + i));   // true，加法之后自动拆箱
        System.out.println(c.equals(a + b));    // true，equals自动拆箱
        System.out.println(g == (a + b));   // true，加法之后自动拆箱
        System.out.println(g.equals(a + b));    // false，自动拆箱之前会先判断类型是否一致
    }
}
