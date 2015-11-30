package com.hong.test.algorithm;

/**
 * Created by Hongwei on 2015/11/30.
 */
public class ComparableBean implements Comparable {
    private int num;

    public ComparableBean(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    @Override
    public int compareTo(Object o) {
        return num - ((ComparableBean)o).num;
    }
}
