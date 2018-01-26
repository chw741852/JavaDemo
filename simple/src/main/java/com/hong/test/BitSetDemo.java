package com.hong.test;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

/**
 * Created by caihongwei on 25/01/2018 5:02 PM.
 */
public class BitSetDemo {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int r = random.nextInt(10);
            list.add(r);
        }
        System.out.print("产生随机数的有：");
        for (Integer i : list) {
            System.out.print(i + ",");
        }
        System.out.println();

        // 将随机数放入BitSet
        BitSet bitSet = new BitSet();
        for (int i = 0; i < 10; i++) {
            bitSet.set(list.get(i));
        }

        // 打印没有随机产生的数
        for (int i = 0; i < 10; i++) {
            if (!bitSet.get(i)) {
                System.out.print(i + ",");
            }
        }
    }
}
