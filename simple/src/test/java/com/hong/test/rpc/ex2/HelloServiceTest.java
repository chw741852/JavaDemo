package com.hong.test.rpc.ex2;

import com.hong.test.rpc.ex2.interfaces.HelloService;
import com.hong.test.rpc.ex2.proxy.RpcProxyFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Hongwei on 2015/11/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:rpc/ex2/consumer.xml")
public class HelloServiceTest {
    @Autowired
    private RpcProxyFactory rpcProxy;
    @Test
    public void hellTest() {
        HelloService helloService = rpcProxy.create(HelloService.class);
        String result = helloService.hello();
        Assert.assertEquals("Hello!", result);
    }
}
