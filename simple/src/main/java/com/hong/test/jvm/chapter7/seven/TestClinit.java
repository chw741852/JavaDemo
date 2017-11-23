package com.hong.test.jvm.chapter7.seven;

/**
 * Created by caihongwei on 21/11/2017 2:51 PM.
 *
 * 模拟<clinit>()初始化方法，<clinit>()为JVM默认执行方法。所有的类初始化时，必将先初始化java.lang.Objec。
 *
 * 此处模拟<clinit>()方法被阻塞
 */
public class TestClinit {
    static class DeadLoopClass {
        static {
            // 不添加if语句将编译报错
            if (true) {
                System.out.println(Thread.currentThread() + " init DeadLoopClass.");
                while (true) {
                }
            }
        }
    }

    public static void main(String[] args) {
        Runnable script = () -> {
            System.out.println(Thread.currentThread() + " start.");
            DeadLoopClass dlc = new DeadLoopClass();
            System.out.println(Thread.currentThread() + " run over.");
        };

        Thread t1 = new Thread(script);
        Thread t2 = new Thread(script);

        t1.start();
        t2.start();
    }
}
