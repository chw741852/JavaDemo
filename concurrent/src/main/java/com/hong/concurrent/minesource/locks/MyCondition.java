package com.hong.concurrent.minesource.locks;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by caihongwei on 02/08/2017 10:45 AM.
 */
public interface MyCondition {
    // 使当前线程在接收到信号或被中断前，一直处于等待状态
    void await() throws InterruptedException;

    // 使当前线程在接收到信号前，一直处于等待状态
    void awaitUninterruptibly();

    // 使当前线程在接收到信号或被中断或到达指定等待时间之前，一直处于等待状态
    long awaitNanos(long nanosTimeout) throws InterruptedException;

    // 使当前线程在接收到信号或被中断或到达指定等待时间之前，一直处于等待状态
    boolean await(long time, TimeUnit timeUnit) throws InterruptedException;

    // 使当前线程在接收到信号或被中断或到达指定等待时间之前，一直处于等待状态
    boolean awaitUntil(Date deadline) throws InterruptedException;

    // 唤醒一个等待线程
    void signal();

    // 唤醒所有等待线程
    void signalAll();
}
