package com.hong.test.clone;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Hongwei on 2015/11/27.
 */
public class Client {
    // 此处传的是对象引用，所以原对象值变了；判断java自定义类传引用
    public void changeA(BeanA a) {
        a.age = 30;
    }
    // 此处会传的是值，所以原对象值未变
    public void changeI(Date date) {
        date = new Date();
        System.out.println(date);
    }

    public static void main(String[] args) throws InterruptedException, CloneNotSupportedException, ParseException {
        Client client = new Client();

        BeanA a = new BeanA();
        a.setAge(20);
        System.out.println("before change: a.age = " + a.getAge());
        client.changeA(a);
        System.out.println("after change: a.age = " + a.getAge());

        Date i = new Date();
        System.out.println("before change: i = " + i);
        client.changeI(i);
        System.out.println("after change: i = " + i);

        //----------------------------------------------------------------------------------------//

        DateFormat format = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ENGLISH);

        BeanA b = new BeanA();
        BeanB beanB = new BeanB();
        beanB.setName("James");
        b.name = "cai";
        b.age = 20;
        b.birthday = new Date();
        b.strs = new String[] {"a", "b"};
        b.setBeanB(beanB);

        BeanA c = (BeanA) b.clone();
        c.name = "caihongwei";
        c.age = 30;
        c.birthday = format.parse("Nov 11, 2005");
        c.strs = new String[] {"c", "d"};
        c.beanB.setName("Tom");

        System.out.println();
        System.out.println("b.name=" + b.name);
        System.out.println("c.name=" + c.name);
        System.out.println("b.strs=" + b.strs[0] + "," + b.strs[1]);
        System.out.println("c.strs=" + c.strs[0] + "," + c.strs[1]);
        System.out.println("b.birthday=" + b.birthday);
        System.out.println("c.birthday=" + c.birthday);
        System.out.println("b.beanB.name=" + b.beanB.getName());
        System.out.println("c.beanB.name=" + c.beanB.getName());
    }
}
