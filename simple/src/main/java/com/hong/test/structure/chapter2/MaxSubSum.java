package com.hong.test.structure.chapter2;

/**
 * Created by cai on 2015/9/13 14:50.
 * 求一个序列中最大子序列和
 */
public class MaxSubSum {
    private static int n = 0;

    // T(N) = O(N^3)
    public int maxSubSum1(int[] arr) {
        int maxSum = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }

                if (sum > maxSum)
                    maxSum = sum;
            }
        }

        return maxSum;
    }

    // T(N) = O(N^2)
    public int maxSubSum2(int[] arr) {
        int maxSum = 0;

        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }

        return maxSum;
    }

    // T(N) = O(N*logN)
    public int maxSubSum3(int[] arr, int left, int right) {
        n++;
        if (left == right)
            return arr[left] < 0 ? 0 : arr[left];

        int center = (left + right) / 2;
        int maxLeftSum = maxSubSum3(arr, 0, center);
        int maxRightSum = maxSubSum3(arr, center + 1, right);

        int maxLeftBorderSum = 0, leftBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += arr[i];
            if (leftBorderSum > maxLeftBorderSum)
                maxLeftBorderSum = leftBorderSum;
        }

        int maxRightBorderSum = 0, rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += arr[i];
            if (rightBorderSum > maxRightBorderSum)
                maxRightBorderSum = rightBorderSum;
        }

        return max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);
    }

    private int max3(int a, int b, int c) {
        int max = 0;
        if (a > max) max = a;
        if (b > max) max = b;
        if (c > max) max = c;

        return max;
    }

    // T(N) = O(N)
    public int maxSubSum4(int[] arr) {
        int maxSum = 0, sum = 0;
        for (int anArr : arr) {
            sum += anArr;
            if (sum > maxSum)
                maxSum = sum;
            else if (sum < 0)
                sum = 0;
        }

        return maxSum;
    }

    public static void main(String[] args) {
        MaxSubSum maxSubSum = new MaxSubSum();
        int[] arr = new int[100000];
        for (int i = 0; i < 100000; i++) {
            if (i % 10 == 0)
                arr[i] = -1 * i;
            else
                arr[i] = i;
        }

        // 大于10000 N/A
//        long start = System.currentTimeMillis();
//        System.out.println("algorithm 1 start...");
//        System.out.println(maxSubSum.maxSubSum1(arr));
//        System.out.println("time: " + (System.currentTimeMillis() - start));

        // 大于100000 N/A
        long start = System.currentTimeMillis();
        System.out.println("algorithm 2 start...");
        System.out.println(maxSubSum.maxSubSum2(arr));
        System.out.println("time: " + (System.currentTimeMillis() - start));

        // 大于50 N/A
//        start = System.currentTimeMillis();
//        System.out.println("algorithm 3 start...");
//        System.out.println(maxSubSum.maxSubSum3(arr, 0, arr.length - 1));
//        System.out.println("time: " + (System.currentTimeMillis() - start));
//        System.out.println("递归调用次数：" + n);
        start = System.currentTimeMillis();
        System.out.println("algorithm 4 start...");
        System.out.println(maxSubSum.maxSubSum4(arr));
        System.out.println("time: " + (System.currentTimeMillis() - start));
    }
}
