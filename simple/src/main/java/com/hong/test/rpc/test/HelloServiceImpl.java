package com.hong.test.rpc.test;

/**
 * Created by Hongwei on 2015/10/30.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
