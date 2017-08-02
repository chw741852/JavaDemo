package com.hong.concurrent.synchronizeds;

/**
 * Created by caihongwei on 02/08/2017 8:45 PM.
 */
public class TestInterrupt {
    static class MyThread implements Runnable {

        @Override
        public void run() {
            System.out.println("my thread");
            try {
                Thread.sleep(1000 * 100);
            } catch (InterruptedException e) {
                System.err.println("线程中断");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        System.out.println("线程是否中断1：" + Thread.interrupted());
        System.out.println("线程是否中断2：" + thread.isInterrupted());
    }
}
