package com.hong.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by caihongwei on 23/01/2018 2:51 PM.
 *
 * Semaphore用于控制线程数量
 */
public class SemaphoreDemo {
    private static final int WINDOW = 4;    // 4个买票窗口
    private static final int TRAVELLER = 40;   // 购票人数

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(4); // 默认非公平锁
        ExecutorService exe = Executors.newFixedThreadPool(WINDOW);
        for (int i = 0; i < TRAVELLER; i++) {
            exe.execute(new MyRunnable(i + 1, semaphore));
        }

        exe.shutdown();
    }

    static class MyRunnable implements Runnable {
        private int id;
        private Semaphore semaphore;

        MyRunnable(int id, Semaphore semaphore) {
            this.id = id;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("旅客 " + id + " 开始买票");
//                System.out.println("当前有" + semaphore.availablePermits() + "人正在买票");
//                System.out.println("当前有" + semaphore.getQueueLength() + "人正在排队");
                Thread.sleep((long) (Math.random() * 5000)); // 随机买票时间
                System.out.println("旅客 " + id + " 买票结束");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
