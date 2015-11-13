package com.hong.test.rpc.ex2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by Hongwei on 2015/11/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:rpc/ex2/provider.xml")
public class Server {
    @Test
    public void helloTest() throws InterruptedException {
        System.out.println("启动....");
        TimeUnit.HOURS.sleep(1);
    }
}
