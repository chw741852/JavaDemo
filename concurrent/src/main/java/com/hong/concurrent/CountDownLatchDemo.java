package com.hong.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by caihongwei on 23/01/2018 11:08 AM.
 *
 * CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。
 * 每当一个线程完成了自己的任务后，计数器的值就会减1。
 * 当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。
 *
 * 因此CountDownLatch这个类用作这样的场景，一个线程在等待其他线程完成各自的工作后再执行。
 * 例如，应用程序的主线程希望一些初始化参数或者属性的子线程都完成后再执行。
 */
public class CountDownLatchDemo {
    private static final int RUNNER_AMOUNT = 5; // 运动员人数

    public static void main(String[] args) {
        CountDownLatch cdlStartgun = new CountDownLatch(1);    // 指令枪
        CountDownLatch cdlRunners = new CountDownLatch(RUNNER_AMOUNT); // 参赛人数
        Runner[] players = new Runner[RUNNER_AMOUNT];
        for (int i = 0; i < RUNNER_AMOUNT; i++) {
            players[i] = new Runner(i + 1, cdlStartgun, cdlRunners);
        }
        ExecutorService exe = Executors.newFixedThreadPool(RUNNER_AMOUNT);
        for (Runner player : players) {
            exe.execute(player);    // 分配线程
        }
        System.out.println("Race begin.");
        cdlStartgun.countDown();
        try {
            cdlRunners.await(); // 等待所有运动员跑完
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Race end.");
        }

        exe.shutdown();
    }

    static class Runner implements Runnable {
        private int id;
        private CountDownLatch startgun;
        private CountDownLatch runners;

        Runner(int id, CountDownLatch cdlStartgun, CountDownLatch cdlRunners) {
            this.id = id;
            this.startgun = cdlStartgun;
            this.runners = cdlRunners;
        }

        @Override
        public void run() {
            try {
                // 等待指令枪响起，cdlStartgun归0，程序开始执行；
                startgun.await();
                // 随机分配时间，即运动员完成时间
                Thread.sleep((long) (Math.random() * 100));
                System.out.println("Player " + id + " arrived.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 本运动员完成跑步，通知cdl_runner减1
                runners.countDown();
            }
        }
    }
}
