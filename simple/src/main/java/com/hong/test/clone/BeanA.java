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
    protected BeanB beanB;

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

    public BeanB getBeanB() {
        return beanB;
    }

    public void setBeanB(BeanB beanB) {
        this.beanB = beanB;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        BeanA a = (BeanA) super.clone();

        // 因为beanB是自定义对象类型，“浅复制”只是复制了beanB的引用；所以这里需要“深复制”
        a.beanB = (BeanB) beanB.clone();
        return a;
    }
}
