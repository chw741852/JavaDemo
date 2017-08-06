package com.hong.test.simple;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Hongwei on 2015/12/26.
 * 验证静态块和构造方法的执行顺序
 */
public class B extends A {
    private static int b = 5;

    public B() {
        System.out.println("In B constructor");
    }

    static {
        System.out.println("In B static -- " + b);
    }

    public static void main(String[] args) {
        new B();

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
}
