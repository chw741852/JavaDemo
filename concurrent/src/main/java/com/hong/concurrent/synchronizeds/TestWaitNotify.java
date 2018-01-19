package com.hong.concurrent.synchronizeds;

/**
 * Created by cai on 2015/9/9 10:00.
 * 测试wait notify
 * 调用一个Object的wait与notify/notifyAll的时候，必须保证调用代码对该Object是同步的，
 * 也就是说必须在作用等同于synchronized(obj){...}的内部才能够去调用obj的wait与notify/notifyAll三个方法，
 * 否则就会报错：
 * java.lang.IllegalMonitorStateException:current thread not owner
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

                lock.notify();  // 随机唤醒一个等待的线程，唤醒的线程又可以去竞争锁；只有当前锁(lock)才能执行唤醒，且只能唤醒当前锁的线程

                try {
                    if (i < 10)
                        lock.wait();    // 线程等待，不去竞争锁
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
