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
        thread.interrupt(); // 中断线程
        System.out.println("线程是否中断2：" + thread.isInterrupted());
        System.out.println("线程是否中断1：" + Thread.interrupted());

//        int h = myThread.hashCode();
//        System.out.println(8 >>> 2);
//        System.out.println(8 ^ 2);
//        System.out.println(h);
//        System.out.println(h >>> 16);
//        System.out.println(h ^ (h >>> 16));
//        int HASH_BITS = 0x7fffffff;
//        System.out.println((h ^ (h >>> 16)) & HASH_BITS);
//        int a = 0;
//        int b = 1;
//        int c = 2;
//
//        a = b = c;
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(c);
    }
}
