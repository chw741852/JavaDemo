package com.hong.test.algorithm;

/**
 * 排序算法
 * Created by Hongwei on 2015/11/28.
 */
public class Sort {
    /**
     * 插入排序
     * O(N^2)
     * @param array 要排序的数组
     * @param <T>   泛型
     */
    public static <T extends Comparable<? super T>>
    void insertionSort(T[] array) {
        int j;
        for (int p = 1; p < array.length; p++) {
            T tmp = array[p];
            for (j = p; j > 0 && tmp.compareTo(array[j-1]) < 0; j--)
                array[j] = array[j-1];
            array[j] = tmp;
        }
    }

    /**
     * 希尔排序
     * @param array
     * @param <T>
     */
    public static <T extends Comparable<? super T>>
    void shellSort(T[] array) {
        int j;
        for (int gap = array.length/2; gap > 0; gap/=2) {
            for (int i = gap; i < array.length; i++) {
                T tmp = array[i];
                for (j = i; j >= gap && tmp.compareTo(array[j - gap]) < 0; j -= gap)
                    array[j] = array[j - gap];
                array[j] = tmp;
            }
        }
    }
}
