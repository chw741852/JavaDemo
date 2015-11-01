package com.hong.test;

/**
 * Created by cai on 2015/9/9 19:46.
 */
public class TestMem {
    public static void main(String[] args) {
        String s1 = "1";
        String s2 = "1";
        String s3 = new String("1");

        Integer A = new Integer(1);
        Integer B = new Integer(1);
        int a = 1;
        int b = 1;

        System.out.println(s1 == s3);   // F
        System.out.println(s1 == s2);   // T
        System.out.println(a == A);     // T
        b = B;
        System.out.println(a == b);     // T

        System.out.println(A == B);     // F
        A = a;
        System.out.println(A == B);     // F
        B = a;
        System.out.println(A == B);     // T
    }
}
