package com.hong.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by caihongwei on 03/08/2017 11:44 AM.
 */
public class MyReadLock implements Lock {
    private final Sync sync = new Sync(1);

    private static class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = 3191328951957435869L;


        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must larger than zero.");
            }

            setState(count);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            for (;;) {
                int current = getState();
                int newCount = current - arg;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (;;) {
                int current = getState();
                int newCount = current + arg;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }
    }

    @Override
    public void lock() {
        sync.tryAcquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) >= 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.tryReleaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    public static void main(String[] args) {
        final Lock lock = new MyReadLock();

        class Worker extends Thread {

            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
//                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            Worker worker = new Worker();
            worker.start();
        }

        new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                        System.out.println("sleep 500");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        try {
            Thread.sleep(20000);
            System.out.println("sleep 20000");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
