package com.hong.test.thread.lock;

/**
 * Created by cai on 2015/9/6 17:26.
 */
public class TestLock extends Thread {
    private int no;
    public TestLock(int no) {
        this.no = no;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i < 10; i++) {
            /*
             * 虽然run方法上加了synchronized关键字，但是由于这里是新建的9个实例，所以不起作用。
             * 如果要对这些线程进行同步，那么这些线程所持有的对象锁应当是共享且唯一的。
             */
            new TestLock(i).start();
            Thread.sleep(1);
        }
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println("No." + no + ": " + i);
        }
    }
}
