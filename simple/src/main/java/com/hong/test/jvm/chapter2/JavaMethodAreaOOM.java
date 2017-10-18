package com.hong.test.jvm.chapter2;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by caihongwei on 18/10/2017 9:50 AM.
 */
public class JavaMethodAreaOOM {
    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();

        }
    }
}
