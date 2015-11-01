package com.hong.test.structure.chapter1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cai on 2015/9/9 20:43.
 */
public class Problem6 {
    public void permute(String str) {
        char[] ss = str.toCharArray();
        permute(ss, 0, ss.length);
    }

    /**
     * abcd交换过程-> 递归  第一层：abcd -> 第二层 bcd -> 第三层 cd -> 第四层 d
     * 所以输出 abcd,abdc,acbd,acdb,adbc,adcb...
     * @param str
     * @param low
     * @param high
     */
    private void permute(char[] str, int low, int high) {
        assert str != null;

        if (low >= high) {
            return;
        } else if (low == high - 1) {
            System.out.println(str);
        } else {
            List<Character> l = new ArrayList<>();
            for (int j = low; j < high; j++) {
                // 防止重复交换
                if (l.contains(str[j])) {
                    continue;
                }
                l.add(str[j]);

                if (j != low) {
                    char temp = str[j];
                    str[j] = str[low];
                    str[low] = temp;
                }

                permute(str, low + 1, high);

                if (j != low) {
                    char temp = str[j];
                    str[j] = str[low];
                    str[low] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Problem6 p = new Problem6();
        long t1 = System.currentTimeMillis();
        p.permute("abcdef");
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }
}
