package com.hong.test.rpc.test;

import com.hong.test.rpc.framework.RpcFramework;

/**
 * Created by Hongwei on 2015/10/30.
 */
public class RpcConsumer {
    public static void main(String[] args) throws Exception {
        HelloService helloService = RpcFramework.refer(HelloService.class, "127.0.0.1", 1234);
        for (int i = 0; i < 10; i++) {
            String result = helloService.sayHello("World" + i);
            System.out.println(result);
            Thread.sleep(1000);
        }
    }
}
