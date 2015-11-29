package com.hong.test.thread.threadlocal;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 非线程安全
 * Created by Hongwei on 2015/11/21.
 */
public class NoThreadLocal implements Runnable {
    private Date startDate;

    @Override
    public void run() {
        startDate = new Date();
        System.out.println("start thread: " + Thread.currentThread().getId() + " " + startDate);
        try {
            TimeUnit.SECONDS.sleep((int)Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread finish " + Thread.currentThread().getId() + " " + startDate);
    }

    public static void main(String[] args) throws InterruptedException {
        NoThreadLocal task = new NoThreadLocal();
        for (int i = 0; i < 10; i++) {
            // 因为是共享task线程，所以打印的startDate是上一个线程的new Date
            Thread thread = new Thread(task);
            thread.start();
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
