package com.hong.test.algorithm;

/**
 * 排序算法
 * Created by Hongwei on 2015/11/28.
 */
public class Sort {
    /**
     * 冒泡排序（稳定排序）
     * 把小的元素往前调或者把大的元素往后调，
     * 比较是相邻的两个元素比较，交换也发生在这两个元素之间。
     * 时间复杂度 O(N^2)，
     *
     * @param array
     * @param <T>
     */
    public static <T extends Comparable<? super T>>
    void bubblingSort(T[] array) {
        T tmp;
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j].compareTo(array[j - 1]) < 0) {
                    tmp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    /**
     * 插入排序（非稳定排序）
     * 每次从无序表中取出第一个元素，把它插入到有序表的合适位置，使有序表仍然有序。
     * 时间复杂度 O(N^2)
     *
     * @param array 要排序的数组
     * @param <T>   泛型
     */
    public static <T extends Comparable<? super T>>
    void insertionSort(T[] array) {
        int j;
        for (int p = 1; p < array.length; p++) {
            T tmp = array[p];
            for (j = p; j > 0 && tmp.compareTo(array[j - 1]) < 0; j--)
                array[j] = array[j - 1];
            array[j] = tmp;
        }
    }

    /**
     * 选择排序
     * 每一趟从待排序的数据元素中选出最小（或最大）的一个元素，
     * 顺序放在已排好序的数列的最后，直到全部待排序的数据元素排完。
     * 时间复杂度 O(N^2)
     *
     * @param array
     * @param <T>
     */
    public static <T extends Comparable<? super T>>
    void choiceSort(T[] array) {
        int min;
        T tmp;
        for (int i = 0; i < array.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[min]) < 0)
                    min = j;
            }
            tmp = array[min];
            array[min] = array[i];
            array[i] = tmp;
        }
    }

    /**
     * 希尔排序
     *
     * @param array
     * @param <T>
     */
    public static <T extends Comparable<? super T>>
    void shellSort(T[] array) {
        int j;
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                T tmp = array[i];
                for (j = i; j >= gap && tmp.compareTo(array[j - gap]) < 0; j -= gap)
                    array[j] = array[j - gap];
                array[j] = tmp;
            }
        }
    }
}
