package com.hong.concurrent.simple;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by caihongwei on 2015/12/28 14:36.
 */
public class TestVolatile {
    public volatile static int count = 0;
    public static AtomicInteger atomicInt = new AtomicInteger(0);

    public static void inc() throws InterruptedException {
        Thread.sleep(1);
        count++;
        atomicInt.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        inc();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        Thread.sleep(10);
        System.out.println("count = " + count); // 最终结果不保证1000，所以volatile不能保证原子性，但是它是对多线程可见性的
        System.out.println("atomicInt = " + atomicInt);
    }
}
