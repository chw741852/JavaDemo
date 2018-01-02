package com.hong.servlet3.chapter1.web.support;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by caihongwei on 02/01/2018 5:05 PM.
 *
 * 随机地等待2秒之内的一个时间
 */
public class LongRunningProcess {
    public void run() {
        try {
            int millis = ThreadLocalRandom.current().nextInt(2000);
            String currentThread = Thread.currentThread().getName();
            System.out.println(currentThread + " sleep for " + millis + " milliseconds.");
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
