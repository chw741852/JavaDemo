package com.hong.test.jvm.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池导致内存溢出
 * 由于jdk1.6是将常量池分配在永久代中，所以通过以下参数可以限制方法区大小；此例只在jdk1.6中起作用
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10
 *
 * Created by caihongwei on 18/10/2017 9:21 AM.
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        // 使用List保持常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<String>();
        // 10MB的PermSize在integer范围内足够产生OOM了
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
