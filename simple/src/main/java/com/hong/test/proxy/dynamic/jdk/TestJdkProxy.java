package com.hong.test.proxy.dynamic.jdk;

/**
 * Created by Hongwei on 2015/10/27.
 */
public class TestJdkProxy {
    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy();
        BookFacade bookProxy = (BookFacade) jdkProxy.bind(new BookFacadeImpl());
        bookProxy.addBook();
        bookProxy.speakHello("World");
    }
}
