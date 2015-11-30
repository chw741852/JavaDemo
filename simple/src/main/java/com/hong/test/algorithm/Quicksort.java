package com.hong.test.algorithm;

/**
 * 快速排序
 * 通过一趟排序将要排序的数据分割成独立的两部分，
 * 其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序。
 * 时间复杂度O(N logN) 最坏 O(N^2)
 *
 * Created by Hongwei on 2015/11/30.
 */
public class Quicksort {
    private static final int CUTOFF = 2;

    public static <T extends Comparable<? super T>>
    void quicksort(T[] array) {
        quicksort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<? super T>>
    void quicksort(T[] array, int left, int right) {
        if (left + CUTOFF < right) {    // 当元素数量小于20时，用插入排序更快
            T pivot = median3(array, left, right);    // 中值分割法查询枢纽元
            int i = left;
            int j = right - 1;

            for (;;){
                while (array[++i].compareTo(pivot) < 0) {}
                while (array[--j].compareTo(pivot) > 0) {}
                if (i < j)
                    swapReference(array, i, j);
                else
                    break;
            }

            swapReference(array, i, right - 1);

            if (i > left) quicksort(array, left, i - 1);
            if (i < right) quicksort(array, i + 1, right);
        } else {
            Sort.insertionSort(array);
        }
    }

    private static <T extends Comparable<? super T>>
    T median3(T[] array, int left, int right) {
        int center = left + right >> 1;

        if (array[center].compareTo(array[left]) < 0)   swapReference(array, center, left);
        if (array[right].compareTo(array[left]) < 0)    swapReference(array, right, left);
        if (array[right].compareTo(array[center]) < 0)  swapReference(array, right, center);

        swapReference(array, center, right - 1);

        return array[right - 1];
    }

    private static <T extends Comparable<? super T>>
    void swapReference(T[] array, int i, int i1) {
        T tmp = array[i];
        array[i] = array[i1];
        array[i1] = tmp;
    }

    public static void main(String[] args) {
        ComparableBean[] array = new ComparableBean[]{
                new ComparableBean(5),
                new ComparableBean(3),
                new ComparableBean(1),
                new ComparableBean(4),
                new ComparableBean(4)
        };
        Quicksort.quicksort(array);
        for (ComparableBean bean : array) {
            System.out.println(bean.getNum());
        }
    }
}
