package com.hong.test.jvm.chapter2;

/**
 * 如果虚拟机在扩展栈的时候无法申请到足够的内存空间，则抛出OutOfMemoryError异常
 * VM Args: -Xss2m
 *
 * 谨慎运行，会导致操作系统死机
 *
 * Created by caihongwei on 17/10/2017 9:13 PM.
 */
public class StackOOM {
    private void dontStop() {}

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(() -> dontStop());
            thread.start();
        }
    }

    public static void main(String[] args) {
        StackOOM oom = new StackOOM();
        oom.stackLeakByThread();
    }
}
