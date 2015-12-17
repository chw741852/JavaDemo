package com.hong.concurrent.synchronizeds;

/**
 * Created by cai on 2015/9/9 10:00.
 * 测试wait notify
 */
public class TestWaitNotify implements Runnable {
    private String s;
    private final String lock;

    public TestWaitNotify(String s, String lock) {
        this.s = s;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                System.out.println(s);

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lock.notify();

                try {
                    if (i < 10)
                        lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String lock = "lock";
        TestWaitNotify t1 = new TestWaitNotify("AAA", lock);
        TestWaitNotify t2 = new TestWaitNotify("BBB", lock);
        TestWaitNotify t3 = new TestWaitNotify("CCC", lock);

        new Thread(t1).start();
        Thread.sleep(100);
        new Thread(t2).start();
        Thread.sleep(100);
        new Thread(t3).start();
        Thread.sleep(100);
    }
}
