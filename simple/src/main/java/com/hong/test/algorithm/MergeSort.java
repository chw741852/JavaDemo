package com.hong.test.algorithm;

/**
 * 归并排序
 * Created by Hongwei on 2015/11/30.
 */
public class MergeSort {
    public static <T extends Comparable<? super T>>
    void mergeSort(T[] array) {
        @SuppressWarnings("unchecked")
        T[] tmpArray = (T[])new Comparable[array.length];
        mergeSort(array, tmpArray, 0, array.length - 1);
    }

    private static <T extends Comparable<? super T>>
    void mergeSort(T[] array, T[] tmpArray, int left, int right) {
        if (left < right) {
            int middle = left + right >> 1;
            mergeSort(array, tmpArray, left, middle);           // 排序左半部分
            mergeSort(array, tmpArray, middle + 1, right);      // 排序右半部分
            merge(array, tmpArray, left, middle + 1, right);    // 合并左右
        }
    }

    private static <T extends Comparable<? super T>>
    void merge(T[] array, T[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        while (leftPos <= leftEnd && rightPos <= rightEnd)
            if (array[leftPos].compareTo(array[rightPos]) <= 0)
                tmpArray[tmpPos++] = array[leftPos++];
            else
                tmpArray[tmpPos++] = array[rightPos++];

        while (leftPos <= leftEnd)
            tmpArray[tmpPos++] = array[leftPos++];
        while (rightPos <= rightEnd)
            tmpArray[tmpPos++] = array[rightPos++];

        for (int i = 0; i < numElements; i++, rightEnd--)
            array[rightEnd] = tmpArray[rightEnd];
    }

    public static void main(String[] args) {
        ComparableBean[] array = new ComparableBean[]{
                new ComparableBean(5),
                new ComparableBean(3),
                new ComparableBean(1),
                new ComparableBean(4),
                new ComparableBean(4)
        };
        MergeSort.mergeSort(array);
        for (ComparableBean bean : array) {
            System.out.println(bean.getNum());
        }
    }
}
