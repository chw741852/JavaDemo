package com.hong.test.jvm.chapter2;

/**
 * String.intern()返回引用测试
 * 测试方法区中的常量池
 *
 * String.intern()是一个Native方法，作用：如果常量池中已包含一个此字符串，则返回常量池中的引用；否则将字符串添加到常量池；
 * jdk1.6会将首次出现的字符串复制到"永久代"中；jdk1.7则会记录在常量池中首次出现的字符串的索引位置并返回；
 *
 * Created by caihongwei on 18/10/2017 9:04 AM.
 */
public class StringIntern {
    public static void main(String[] args) {
        String s1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(s1.intern() == s1);  // jdk1.7：true，因为是首次出现，所以intern()返回索引位置与s1相等

        String s2 = new StringBuilder("ja").append("va").toString();
        System.out.println(s2.intern() == s2);  // jdk1.7: false, 因为"java"不是首次出现，intern()返回的是首次出现的索引位置，所以与s2不相等

        String s3 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(s3.intern() == s3);  // jdk1.7: false
        System.out.println(s3.intern() == s1);  // jdk1.7: true

        /*
         * 如果是jdk1.6，上面所有都是false，intern()都会将首次出现的字符串复制到"永久代"中并返回索引，
         * 而StringBuilder创建的对象在Java堆中，所以必然不是同一个索引；
         */
    }
}
