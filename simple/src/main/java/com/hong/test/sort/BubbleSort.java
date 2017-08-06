package com.hong.test.sort;

/**
 * 冒泡排序
 * Created by caihongwei on 06/08/2017 10:11 PM.
 */
public class BubbleSort {
    /**
     * 冒泡排序
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     */
    public static void sort(int[] numbers) {
        int temp;
        int size = numbers.length;
        int[] nums = numbers.clone();
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }

        for (int num : nums) {
            System.out.print(num + ",");
        }
    }

    /**
     * 冒泡排序？
     */
    public static void bubbleSort2(int[] numbers) {
        int temp;
        int size = numbers.length;
        int[] nums = numbers;
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (nums[i] > nums[j]) {
                    temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }

        for (int num : nums) {
            System.out.print(num + ",");
        }
    }
}
