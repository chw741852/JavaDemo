package com.hong.test.rpc.ex2.interfaces;

import com.hong.test.rpc.ex2.support.RpcService;

/**
 * Created by Hongwei on 2015/11/12.
 */
@RpcService(value = "helloService", inf = HelloService.class)
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello() {
        return "Hello!";
    }
}
