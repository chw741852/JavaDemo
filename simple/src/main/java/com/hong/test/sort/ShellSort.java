package com.hong.test.sort;

/**
 * 希尔排序
 * 根据需求，如果你想要结果从大到小排列，它会首先将数组进行分组，然后将较大值移到前面，较小值移到后面，
 * 最后将整个数组进行插入排序，这样比起一开始就用插入排序减少了数据交换和移动的次数，
 * 可以说希尔排序是加强版的插入排序。
 * 拿数组5, 2, 8, 9, 1, 3，4来说，数组长度为7，当increment为3时，数组分为两个序列
 * 5，2，8和9，1，3，4，第一次排序，9和5比较，1和2比较，3和8比较，4和比其下标值小increment的数组值相比较
 * 此例子是按照从大到小排列，所以大的会排在前面，第一次排序后数组为9, 2, 8, 5, 1, 3，4
 * 第一次后increment的值变为3/2=1，此时对数组进行插入排序，实现数组从大到小排
 *
 * 时间复杂度 O(n²)
 * 空间复杂度 O(1)
 *
 * Created by caihongwei on 08/08/2017 10:41 AM.
 */
public class ShellSort {
    public static void sort(int[] numbers) {
        int size = numbers.length;
        for (int increment = size / 2; increment > 0; increment /= 2) {
            int j,temp;
            for (int i = increment; i < size; i++) {
                temp = numbers[i];
                for (j = i; j >= increment; j -= increment) {
                    if (temp < numbers[j - increment]) {    // 从小到大排序
                        numbers[j] = numbers[j - increment];
                    } else {
                        break;
                    }
                }
                numbers[j] = temp;
            }
        }

        System.out.print("希尔排序：");
        for (int number : numbers) {
            System.out.print(number + ",");
        }
        System.out.println();
    }
}
