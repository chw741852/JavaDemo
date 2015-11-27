package com.hong.test.clone;

import java.util.Date;

/**
 * Created by Hongwei on 2015/11/27.
 */
public class BeanA implements Cloneable {
    protected String name;
    protected int age;
    protected Date birthday;
    protected String[] strs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String[] getStrs() {
        return strs;
    }

    public void setStrs(String[] strs) {
        this.strs = strs;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        BeanA a = (BeanA) super.clone();
        return a;
    }
}
