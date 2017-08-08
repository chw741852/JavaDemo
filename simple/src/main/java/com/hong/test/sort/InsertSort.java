package com.hong.test.sort;

/**
 * 插入排序
 * 从第一个元素开始，该元素可以认为已经被排序
 * 取出下一个元素，在已经排序的元素序列中从后向前扫描
 * 如果该元素（已排序）大于新元素，将该元素移到下一位置
 * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
 * 将新元素插入到该位置中
 * 重复步骤2
 * 时间复杂度 O(n²)
 * 空间复杂度 O(1)
 * Created by caihongwei on 07/08/2017 8:02 PM.
 */
public class InsertSort {
    public static void sort(int[] numbers) {
        int size = numbers.length;
        int j, temp;
        for (int i = 0; i < size; i++) {
            temp = numbers[i];
            for (j = i; j > 0; j--) {
                if (temp < numbers[j - 1]) {
                    numbers[j] = numbers[j - 1];
                } else {
                    break;
                }
            }
            numbers[j] = temp;
        }

        System.out.print("插入排序：");
        for (int number : numbers) {
            System.out.print(number + ",");
        }
        System.out.println();
    }
}
