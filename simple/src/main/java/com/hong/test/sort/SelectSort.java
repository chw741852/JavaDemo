package com.hong.test.sort;

/**
 * 选择排序
 * 在未排序序列中找到最小元素，存放到排序序列的起始位置，
 * 再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾，
 * 以此类推，直到所有元素均排序完毕。
 * 时间复杂度 O(n²)
 * 空间复杂度 O(1)
 * Created by caihongwei on 07/08/2017 4:30 PM.
 */
public class SelectSort {
    public static void sort(int[] numbers) {
        int temp;
        int size = numbers.length;
        for (int i = 0; i < size; i++) {
            int k = i;
            for (int j = size - 1; j > i; j--) {
                if (numbers[k] > numbers[j]) {
                    k = j;
                }
            }
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
        }

        System.out.print("选择排序：");
        for (int number : numbers) {
            System.out.print(number + ",");
        }
        System.out.println();
    }
}
