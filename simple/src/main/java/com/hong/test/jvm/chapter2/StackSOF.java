package com.hong.test.jvm.chapter2;

/**
 * 虚拟机栈和本地方法栈OOM测试
 * 1. 如果线程请求的栈深度大于虚拟机所允许的最大深度，将抛出StackOverflowError异常
 * 2. 如果虚拟机在扩展栈的时候无法申请到足够的内存空间，则抛出OutOfMemoryError异常
 *
 * 此处测试出StackOverflowError异常
 *
 * -Xss 设置虚拟机栈大小
 * VM Args: -Xss128k
 * Created by caihongwei on 17/10/2017 8:51 PM.
 */
public class StackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        StackSOF stackSOF = new StackSOF();
        try {
            stackSOF.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length: " + stackSOF.stackLength);
            throw e;
        }
    }
}
