package com.hong.test.structure.chapter1;

/**
 * Created by cai on 2015/9/10 9:32.
 */
public class Fibonacci {
    public int fibonacci(int n) {
        if (n <= 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        for (int i = 1; i < 10; i++) {
            System.out.print(fibonacci.fibonacci(i) + ",");
        }
    }
}
