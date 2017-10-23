package com.hong.test.jvm.chapter3;

/**
 * 内存分配
 *
 * Created by caihongwei on 19/10/2017 8:24 PM.
 */
public class MinorGC {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {

    }

    /**
     * 新生代GC
     *
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * 现在堆内存大小为20M，其中10M分配给新生代，剩余10M给老年代
     * SurvivorRatio=8说Survivor区的空间比例为8:1
     */
    private static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
    }
}
