package com.hong.concurrent.synchronizeds;

/**
 * Created by caihongwei on 22/01/2018 3:58 PM.
 */
public class TestJoin {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyJoin(), "t1");
        Thread t2 = new Thread(new MyJoin(), "t2");
        Thread t3 = new Thread(new MyJoin(), "t3");

        t1.start();
        try {
            t1.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t3.start();

        try {
            t1.join();
            t3.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads are dead, exiting main thread");
    }

    static class MyJoin implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread started:::"+Thread.currentThread().getName());
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread ended:::"+Thread.currentThread().getName());
        }
    }
}
