package com.hong.test.sort;

/**
 * 归并排序
 * 将两个（或两个以上）有序表合并成一个新的有序表 即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列
 * 时间复杂度为O(nlogn)
 * 稳定排序方式
 * Created by caihongwei on 08/08/2017 2:25 PM.
 */
public class MergeSort {
    public static void sort(int nums[]) {
        sort(nums, 0, nums.length - 1);
        System.out.print("归并排序：");
        for (int num : nums) {
            System.out.print(num + ",");
        }
        System.out.println();
    }

    /**
     * 排序
     * @param nums  待排序数组
     * @param low   数组的开始位置
     * @param high  数组的结束位置
     * @return int[]
     */
    private static int[] sort(int nums[], int low, int high) {
        int middle = (low + high) / 2;

        if (low < high) {
            sort(nums, low, middle);
            sort(nums, middle + 1, high);
            merge(nums, low, middle, high);
        }

        return nums;
    }

    private static void merge(int[] nums, int low, int middle, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;    // 左指针
        int j = middle + 1; // 右指针
        int k = 0;

        while (i <= middle && j <= high) {
            if (nums[i] < nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        while (i <= middle) {
            temp[k++] = nums[i++];
        }
        while (j <= high) {
            temp[k++] = nums[j++];
        }
//        System.out.println("nums.length=" + nums.length + ", temp.length=" + temp.length + ", low=" + low + ", high=" + high);
        System.arraycopy(temp, 0, nums, low, temp.length);
    }
}
