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
            new Thread(() -> {
                try {
                    inc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(10);

        System.out.println("atomicInt = " + atomicInt); // 原子性
        /*
         *  1、最终结果跟预期不一样，所以volatile不能保证原子性
         *   即：在read load之后，如果主内存count变量发生修改之后，线程工作内存中的值由于已经加载，不会产生对应的变化
         *  2、但是它是对多线程可见性的
         *   即：jvm虚拟机只是保证从主内存加载到线程工作内存的值是最新的
         */
        System.out.println("count = " + count);
    }
}
