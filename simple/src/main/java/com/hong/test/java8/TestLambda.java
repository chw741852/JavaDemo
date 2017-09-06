package com.hong.test.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

/**
 * Created by caihongwei on 06/09/2017 11:52 AM.
 */
public class TestLambda {

    // 普通lambda表达式
    private static void t1 () {
        String [] ss = new String[]{"1", "b", "c"};
        Arrays.asList(ss).forEach(System.out::println);
        Arrays.asList("e", "f", "g").forEach((String s) -> {
            System.out.println(s);
        });
        Arrays.asList("a", "b", "c").forEach(s -> {
            System.out.print(s);
            System.out.println(",");
        });

        // 排序
        Arrays.asList(ss).sort(Comparator.naturalOrder());
        Arrays.asList(ss).sort(String::compareTo);
        Arrays.asList(ss).sort((e1, e2) -> e1.compareTo(e2));
        Arrays.asList(ss).sort((String e1, String e2) -> {
            int result = e1.compareTo(e2);
            return result;
        });
    }

    // 空指针处理
    private static void t2() {
        Optional<String> s =  Optional.empty();
        System.out.println(s.isPresent());
        System.out.println(s.orElseGet(() -> "[null]"));
        System.out.println(s.map(a -> "Hey " + a + "!").orElse("Hey Stranger!"));
    }

    public static void main(String[] args) {
        t1();
        t2();
    }
}
