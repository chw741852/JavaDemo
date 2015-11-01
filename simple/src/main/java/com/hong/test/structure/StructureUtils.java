package com.hong.test.structure;

/**
 * Created by Hongwei on 2015/9/30.
 */
public class StructureUtils {
    /**
     * 得到某个数的下一个素数
     * @param n
     * @return
     */
    public static int nextPrime(int n) {
        if (n < 3) n = 3;
        while (!isPrime(n)) {
            n++;
        }

        return n;
    }

    /**
     * 获取2个数之间的所有素数
     * @param min
     * @param max
     */
    public static void findAllPrime(int min, int max) {
        System.out.println(min + "到" + max + "之间的素数为：");
        for (int i = min; i <= max; i++) {
            if (isPrime(i))
                System.out.print(i + " ");
        }
    }

    /**
     * 验证是否素数
     * @param n
     * @return
     */
    private static boolean isPrime(int n) {
        if (n == 2) return true;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int n = 110;
        int prime = nextPrime(n);
        System.out.println(n + "的下一个素数为" + prime);

        findAllPrime(51, 121);
    }
}
