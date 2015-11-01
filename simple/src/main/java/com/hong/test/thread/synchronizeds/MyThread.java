package com.hong.test.thread.synchronizeds;

/**
 * Created by cai on 2015/9/8 10:26.
 * 建立三个线程，A线程打印10次A，B线程打印10次B,C线程打印10次C，要求线程同时运行，交替打印10次ABC。
 */
public class MyThread implements Runnable {
    private String name;
    private final Object prev;
    private final Object self;

    public MyThread(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.println(name);
                    count--;

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    self.notify();
                }

                try {
                    if (count > 0)
                        prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        new Thread(new MyThread("A", c, a)).start();
        Thread.sleep(10);
        new Thread(new MyThread("B", a, b)).start();
        Thread.sleep(10);
        new Thread(new MyThread("C", b, c)).start();
        Thread.sleep(10);
    }
}
