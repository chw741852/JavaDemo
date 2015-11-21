package com.hong.test.thread.threadlocal;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 线程安全
 * Created by Hongwei on 2015/11/21.
 */
public class WithThreadLocal implements Runnable {
    private static ThreadLocal<Date> startDate = new ThreadLocal<Date>() {
        protected Date initialValue() {
            return new Date();
        }
    };

    @Override
    public void run() {
        System.out.println("start thread: " + Thread.currentThread().getId() + " " + startDate.get());
        try {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread finish " + Thread.currentThread().getId()
                + " " + startDate.get());
    }

    public static void main(String[] args) {
        WithThreadLocal task = new WithThreadLocal();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(task);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
