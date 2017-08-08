package com.hong.test.sort;

/**
 * Created by caihongwei on 06/08/2017 10:59 PM.
 */
public class Main {
    public static void main(String[] args) {
        int[] numbers = {10,20,15,0,6,7,2,1,-5,55,-10};
        BubbleSort.sort(numbers.clone());
        QuickSort.sort(numbers.clone());
        SelectSort.sort(numbers.clone());
        InsertSort.sort(numbers.clone());
        ShellSort.sort(numbers.clone());

        System.out.print("原始数组：");
        for (int number : numbers) {
            System.out.print(number + ",");
        }
        System.out.println();
    }
}
