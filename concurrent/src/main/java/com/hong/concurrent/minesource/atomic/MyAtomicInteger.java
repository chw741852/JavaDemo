package com.hong.concurrent.minesource.atomic;

import sun.misc.Unsafe;

import java.io.Serializable;

/**
 * Created by caihongwei on 02/08/2017 9:37 AM.
 */
public class MyAtomicInteger extends Number implements Serializable {
    private static final Unsafe UNSAFE = Unsafe.getUnsafe();
    private static final long valueOffset;

    static {
        try {
            valueOffset = UNSAFE.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }

    private volatile int value;

    public MyAtomicInteger(int initialValue) {
        value = initialValue;
    }

    public MyAtomicInteger(){
    }

    public final int get() {
        return value;
    }

    /**
     * 利用Unsafe做CAS操作，此为线程安全的原子操作，且非阻塞
     * @param expect
     * @param update
     * @return
     */
    public boolean compareAndSet(int expect, int update) {
        return UNSAFE.compareAndSwapInt(this, valueOffset, expect, update);
    }

    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public long longValue() {
        return 0;
    }

    @Override
    public float floatValue() {
        return 0;
    }

    @Override
    public double doubleValue() {
        return 0;
    }
}
