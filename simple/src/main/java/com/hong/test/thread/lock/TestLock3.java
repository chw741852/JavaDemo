package com.hong.test.thread.lock;

/**
 * Created by cai on 2015/9/6 17:54.
 */
public class TestLock3 extends Thread {
    private int no;

    public TestLock3(int no) {
        this.no = no;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i < 10; i++) {
            new TestLock3(i).start();
            Thread.sleep(1);
        }
    }

    /**
     * 因为是静态方法，只会有一个实例，所以锁起作用
     * @param no
     */
    private static synchronized void abc(int no) {
        for (int i = 0; i < 10000; i++) {
            System.out.println("No." + no + ":" + i);
        }
    }

    @Override
    public void run() {
        abc(no);
    }
}
