package com.hong.test.clone;

/**
 * Created by Hongwei on 2015/11/27.
 */
public class BeanB implements Cloneable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
