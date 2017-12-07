package com.hong.test.currentlimit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * Created by caihongwei on 07/12/2017 3:01 PM.
 *
 * 平滑限流
 *
 * Guava RateLimiter提供了令牌桶算法实现。
 * 计数器限流方式不能很好地应对突发请求，即瞬间请求可能都被允许从而导致一些问题；
 * 因此在一些场景中需要对突发请求进行整形, 整形为平均速率请求处理（比如5r/s，则每隔200毫秒处理一个请求，平滑了速率）。
 */
public class SmoothBursty {
    public static void main(String[] args) throws InterruptedException {
//        limit();
//        limit2();
//        limit3();
        warmingLimit();
    }

    /*
     * 1、RateLimiter.create(5) 表示桶容量为5且每秒新增5个令牌，即每隔200毫秒新增一个令牌；
     * 2、limiter.acquire()表示消费一个令牌，如果当前桶中有足够令牌则成功（返回值为0），如果桶中没有令牌则暂停一段时间，
     * 比如发令牌间隔是200毫秒，则等待200毫秒后再去消费令牌（如测试用例返回的为0.198239，差不多等待了200毫秒桶中才有令牌可用），
     * 这种实现将突发请求速率平均为了固定请求速率。
     */
    private static void limit() {
        RateLimiter limiter = RateLimiter.create(5);    // 每秒处理5个请求
        for (int i = 0; i < 6; i++) {
            /*
             * 0.0 直接返回0，代表处理成功
             * 0.193685 代表处理时长为0.19秒
             * 0.195793
             * 0.195693
             * 0.195449
             * 0.195727
             */
            System.out.println(limiter.acquire());
        }
        System.out.println("limit() done.");
    }

    private static void limit2() {
        RateLimiter limiter = RateLimiter.create(5);
        System.out.println(limiter.acquire(6)); // 0.0；瞬间消费6个
        System.out.println(limiter.acquire(20)); // 1.199927；上面消费6个令牌（此处等待耗时200 * 6毫秒后执行）
        System.out.println(limiter.acquire(1)); // 3.998226；上面消费20个令牌
        System.out.println(limiter.acquire(1)); // 0.198092
        System.out.println("limit2() done.");
    }

    private static void limit3() throws InterruptedException {
        RateLimiter limiter = RateLimiter.create(2);
        System.out.println(limiter.acquire());  // 0.0
        Thread.sleep(2000);
        System.out.println(limiter.acquire());  // 0.0
        System.out.println(limiter.acquire());  // 0.0
        System.out.println(limiter.acquire());  // 0.0
        System.out.println(limiter.acquire());  // 0.499762
        System.out.println(limiter.acquire());  // 0.496065
        System.out.println("limit3() done.");
    }

    private static void warmingLimit() throws InterruptedException {
        // permitsPerSecond: 5; 每秒新增5个令牌
        // warmupPeriod: 1000; 表示在从冷启动速率过渡到平均速率的时间间隔
        // unit: ; 参数warmupPeriod的时间单位
        RateLimiter limiter = RateLimiter.create(5, 1000, TimeUnit.MILLISECONDS);
        for (int i = 0; i < 5; i++) {
            System.out.println(limiter.acquire());
        }
        Thread.sleep(1000);
        for (int i = 0; i < 5; i++) {
            System.out.println(limiter.acquire());
        }
        /*
         * 0.0
         * 0.517149
         * 0.352869
         * 0.215405
         * 0.19708
         * 0.0
         * 0.366418
         * 0.21902
         * 0.19471
         * 0.197777
         * 速率是梯形上升速率的，也就是说冷启动时会以一个比较大的速率慢慢到平均速率；然后趋于平均速率（梯形下降到平均速率）；
         * 可以通过调节warmupPeriod参数实现一开始就是平滑固定速率。
         */
    }
}
