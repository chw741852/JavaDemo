package com.hong.test.sort;

/**
 * 快速排序
 * 时间复杂度 O(n*log2n)
 * 空间复杂度 O(log2n)~O(n)
 * Created by caihongwei on 06/08/2017 10:46 PM.
 */
public class QuickSort {
    public static void sort(int[] numbers) {
        quickSort(numbers, 0, numbers.length - 1);
        System.out.print("快速排序：");
        for (int number : numbers) {
            System.out.print(number + ",");
        }
        System.out.println();
    }

    private static void quickSort(int[] numbers, int low, int high) {
        if (low < high) {
            int middle = getMiddle(numbers, low, high);
            quickSort(numbers, low, middle - 1);
            quickSort(numbers, middle + 1, high);
        }
    }

    // int[] numbers = {10,20,15,0,6,7,2,1,-5,55,-10};
    private static int getMiddle(int[] numbers, int low, int high) {
        int temp = numbers[low];    // 第一位作为中轴
        while (low < high) {
            while (low < high && temp < numbers[high]) {
                high--;
            }
            numbers[low] = numbers[high];   // 比中轴小的记录到低端
            while (low < high && numbers[low] < temp) {
                low++;
            }
            numbers[high] = numbers[low];   // 比中轴大的记录到高端
//            for (int number : numbers) {
//                System.out.print(number + ",");
//            }
//            System.out.println();
        }
        numbers[low] = temp;

//        System.out.println("end " + low);

        return low;
    }
}
