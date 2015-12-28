package com.hong.concurrent.atomicclass;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by caihongwei on 2015/12/23 11:12.
 */
public class TestAtomicLong {
    private static AtomicLong count = new AtomicLong(0);

    public static void main(String[] args) {
        System.out.println(count.incrementAndGet());
        System.out.println(count.incrementAndGet());
        System.out.println(count);
    }
}
