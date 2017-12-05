package com.hong.test.jvm.chapter8.eleven;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Created by caihongwei on 04/12/2017 8:31 PM.
 */
public class MethodHandleTest {
    static class ClassA {
        public void println(String s) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws Throwable {
        Object o = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();

        getPrintln(o).invokeExact("itest");
    }

    private static MethodHandle getPrintln(Object reveiver) throws Throwable {
        MethodType mt = MethodType.methodType(void.class, String.class);

        return MethodHandles.lookup().findVirtual(reveiver.getClass(), "println", mt).bindTo(reveiver);
    }
}
