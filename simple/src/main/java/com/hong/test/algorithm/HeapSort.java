package com.hong.test.algorithm;

/**
 * 堆排序
 * 通过下滤得到最小堆根，将其与最后的元素调换并堆大小逻辑减1，再次下滤。
 * 时间复杂度 O(N*logN)
 * Created by Hongwei on 2015/11/28.
 */
public class Heapsort {
    public static <T extends Comparable<? super T>>
    void heapsort(T[] a) {
        for (int i = a.length / 2; i >= 0; i--) {
            // 构建堆
            percDown(a, i, a.length);
        }
        for (int i = a.length - 1; i > 0; i--) {
            swapReference(a, 0, i); // deleteMax
            // 重排
            percDown(a, 0, i);
        }
    }

    private static <T extends Comparable<? super T>> void swapReference(T[] a, int start, int end) {
        T tmp = a[start];
        a[start] = a[end];
        a[end] = tmp;
    }

    /**
     * 二叉堆 下滤
     * @param a 下滤的数组
     * @param i 当前节点
     * @param length 逻辑大小
     * @param <T> 泛型
     */
    private static <T extends Comparable<? super T>> void percDown(T[] a, int i, int length) {
        T tmp;
        int child;
        for (tmp = a[i]; leftChild(i) < length; i = child) {
            child = leftChild(i);
            if (child != length - 1 && a[child].compareTo(a[child + 1]) < 0)
                child++;
            if (tmp.compareTo(a[child]) < 0)
                a[i] = a[child];
            else
                break;
        }
        a[i] = tmp;
    }

    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    public static void main(String[] args) {
        ComparableBean[] array = new ComparableBean[]{
                new ComparableBean(5),
                new ComparableBean(3),
                new ComparableBean(1),
                new ComparableBean(4),
                new ComparableBean(4)
        };
        Heapsort.heapsort(array);
        for (ComparableBean bean : array) {
            System.out.println(bean.getNum());
        }
    }
}
