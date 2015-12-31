package com.hong.concurrent.bean;

/**
 * Created by caihongwei on 2015/12/31 10:55.
 */
public class PersonInfo {
    private String name;
    private int age;

    public PersonInfo(String name, int age) {
        this.name = name;
        this.age = age;
    }

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
}
