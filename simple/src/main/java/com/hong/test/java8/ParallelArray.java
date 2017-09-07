package com.hong.test.java8;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by caihongwei on 07/09/2017 11:21 AM.
 * java 8并行数组排序
 */
public class ParallelArray {
    public static void main(String[] args) {
        long[] array = new long[2000];
        // 添加2000个随机数
        Arrays.parallelSetAll(array, index -> ThreadLocalRandom.current().nextLong(200000));
        // 打印前10条
        Arrays.stream(array).limit(10).forEach(num -> System.out.print(num + " "));
        System.out.println();

        // 并行排序
        Arrays.parallelSort(array);
        // 打印前10条
        Arrays.stream(array).limit(10).forEach(num -> System.out.print(num + " "));
    }
}
