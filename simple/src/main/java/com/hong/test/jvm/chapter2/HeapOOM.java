package com.hong.test.jvm.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * Java堆内存溢出异常测试
 * 分配堆内存大小为20M，且不能自动扩展（即：将堆的最小值-Xms和最大值-Xms设置为一样大）
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * Created by caihongwei on 17/10/2017 8:13 PM.
 */
public class HeapOOM {
    static class OOMObject {}

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}
