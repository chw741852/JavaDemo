package com.hong.test.jvm.chapter7.eight;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by caihongwei on 21/11/2017 3:05 PM.
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String filename = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(filename);
                if (is == null) {
                    return super.loadClass(name);
                }
                try {
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object o = myLoader.loadClass("com.hong.test.jvm.chapter7.eight.ClassLoaderTest").newInstance();
        System.out.println(o.getClass());
        // false；当运行此main方法时，系统加载了此类
        System.out.println(o instanceof com.hong.test.jvm.chapter7.eight.ClassLoaderTest);

        Object o2 = myLoader.loadClass("com.hong.test.TestMem").newInstance();
        System.out.println(o2.getClass());
        // true；此时系统并未加载TestMem
        System.out.println(o2 instanceof com.hong.test.TestMem);
    }
}
