package com.hong.test.proxy.dynamic.jdk;

/**
 * 书籍委托类
 *
 * Created by Hongwei on 2015/10/27.
 */
public class BookFacadeImpl implements BookFacade {
    @Override
    public void addBook() {
        System.out.println("新增书籍...");
    }

    @Override
    public void speakHello(String msg) {
        System.out.println("Hello " + msg);
    }
}
