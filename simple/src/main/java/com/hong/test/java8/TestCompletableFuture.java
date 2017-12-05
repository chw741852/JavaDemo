package com.hong.test.java8;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by caihongwei on 27/11/2017 10:32 AM.
 */
public class TestCompletableFuture {
    private static double test(int i) {
        System.out.println("index: " + i);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("test end " + i);
        return new Random().nextDouble() + i;
    }

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        ExecutorService fixedExecutor = Executors.newFixedThreadPool(20);
        for (int j = 0; j < 3; j++) {
            int index = j;
            CompletableFuture.supplyAsync(() -> test(index), fixedExecutor);
//            CompletableFuture.supplyAsync(() -> test(index), fixedExecutor)
//                    .whenComplete((v, t) -> System.out.println(v))
//                    .thenCompose(i -> CompletableFuture.supplyAsync(() -> i + 10))
//                    .thenAccept(v -> System.out.println("thenAccept value is -> " + v))
//                    .thenRun(() -> System.out.println("thenRun -> do some end task"));
        }


        System.out.println("I'm done. " + (System.currentTimeMillis() - s));
    }
}
