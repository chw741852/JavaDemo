package com.hong.test.thread.lock;

/**
 * Created by cai on 2015/9/6 17:36.
 */
public class TestLock2 extends Thread {
    private int no;
    private final String lock;

    public TestLock2(int no, String lock) {
        this.no = no;
        this.lock = lock;
    }

    public static void main(String[] args) throws InterruptedException {
        /*
         * 应为所有实例的lock指向的是同一个内存，所以此处锁起作用了
         */
        String lock = "lock";
        for (int i = 1; i < 10; i++) {
            new TestLock2(i, lock).start();
            Thread.sleep(1);
        }
    }

    @Override
    public void run() {
        synchronized (lock) {
            for (int i = 0; i < 10000; i++) {
                System.out.println("No." + no + ":" + i);
            }
        }
    }
}
