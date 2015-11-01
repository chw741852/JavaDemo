package com.hong.test.proxy.dynamic.cglib;

import com.hong.test.proxy.dynamic.jdk.BookFacade;
import com.hong.test.proxy.dynamic.jdk.BookFacadeImpl;

/**
 * Created by Hongwei on 2015/10/27.
 */
public class TestCglib {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        BookFacadeImpl1 bookCglib = (BookFacadeImpl1) cglibProxy.getInstance(new BookFacadeImpl1());
        bookCglib.addBook();

        BookFacade bookFacade = (BookFacade) cglibProxy.getInstance(new BookFacadeImpl());
        bookFacade.addBook();
        bookFacade.speakHello("World");
    }
}
