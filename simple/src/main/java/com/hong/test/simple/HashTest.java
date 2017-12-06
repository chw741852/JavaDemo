package com.hong.test.simple;

import java.util.*;

/**
 * Created by Hongwei on 2015/12/26.
 * 验证静态块和构造方法的执行顺序
 */
public class HashTest extends A {
    private static int b = 5;

    public HashTest() {
        System.out.println("In HashTest constructor");
    }

    static {
        System.out.println("In HashTest static -- " + b);
    }

    public static void main(String[] args) {
        new HashTest();

//        Map<Integer, Integer> map = new MyTreeMap<>();
//        map.put(25, 25);
//        map.put(21, 21);
//        map.put(27, 27);
////        map.put(20, 20);
//
//        System.out.println(map);
//
//
//        Map<Integer, Integer> map1 = new HashMap<>();
//        map1.put(25,25);
//
//        Map<Integer, Integer> map2 = new ConcurrentHashMap<>();
//        map.put(25, 25);
//        map.putIfAbsent(25, 25);

        int hash = hash("196");
        int n = 16;
        int i = (n - 1) & hash;
        System.out.println(hash);
        System.out.println(i);

        Set<String> s = new HashSet<>();
        s.add("");
        List<String> list = new ArrayList<>();
        list.add("s");
        list.get(0);

        String s1 = "0 1 2 3 4 5 6 7 8 9 a b c d e f";
        int c = 190;
        Arrays.stream(s1.split(" ")).forEach(str -> System.out.print(endHash(str, c) + " "));
        System.out.println();
        System.out.println(hash(c-1)); // hash()方法对int类型不起作用
        Arrays.stream(s1.split(" ")).forEach(str -> System.out.print((Integer.parseInt(str, 16) & hash(c-1)) + " "));
        System.out.println();
        System.out.println(5 & 3);  // 数字之间"&"运算，大小不会超过第二个参数
    }

    /**
     * HashMap中的hash算法
     * @param key
     * @return
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 10);
    }

    public static int endHash(String s, int hashFactor) {
        String endChar = s.toUpperCase().substring(s.length() - 1);
        int index = Integer.parseInt(endChar, 16) & hashFactor - 1;
        return index;
    }
}
