package com.hong.spring.schema;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Hongwei on 2015/11/1.
 */
public class TestPeopleXsd {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/spring.xml");
        People people = (People)ctx.getBean("hongsource");
        System.out.println(people.getId());
        System.out.println(people.getName());
        System.out.println(people.getAge());
    }
}
