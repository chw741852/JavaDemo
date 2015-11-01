package com.hong.test.rpc.test;

import com.hong.test.rpc.framework.RpcFramework;

/**
 * Created by Hongwei on 2015/10/30.
 */
public class RpcProvider {
    public static void main(String[] args) throws Exception {
        HelloService helloService = new HelloServiceImpl();
        RpcFramework.export(helloService, 1234);
    }
}
