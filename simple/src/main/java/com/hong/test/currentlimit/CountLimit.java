package com.hong.test.currentlimit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by caihongwei on 07/12/2017 2:08 PM.
 *
 * 计数器暴力限流
 */
public class CountLimit {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LoadingCache<Long, AtomicLong> counter = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)  // 每秒数据缓存2秒
                .build(new CacheLoader<Long, AtomicLong>() {
                    @Override
                    public AtomicLong load(Long seconds) throws Exception {
                        return new AtomicLong(0);
                    }
                });
        for (int i = 0; i < 1005; i++) {
            limit(counter);
        }
    }

    private static boolean limit(LoadingCache<Long, AtomicLong> counter) throws ExecutionException {
        // 每秒限流1000
        long limit = 1000;
        long currentSecond = System.currentTimeMillis() / 1000;
        if (counter.get(currentSecond).incrementAndGet() > limit) {
            System.out.println("限流了");
            return false;
        } else {
            // 业务处理
            System.out.println(counter.get(currentSecond).get()  + ": " + currentSecond);
            return true;
        }
    }
}
