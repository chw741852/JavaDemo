package com.hong.test.proxy.statics;

/**
 * 测试account类
 *
 * 代理模式是常用的java设计模式，他的特征是代理类与委托类有同样的接口，
 * 代理类主要负责为委托类预处理消息、过滤消息、把消息转发给委托类，以及事后处理消息等。
 * 代理类与委托类之间通常会存在关联关系，一个代理类的对象与一个委托类的对象关联，
 * 代理类的对象本身并不真正实现服务，而是通过调用委托类的对象的相关方法，来提供特定的服务。
 *
 * Created by Hongwei on 2015/10/27.
 */
public class TestAccount {
    public static void main(String[] args) {
        AccountImpl account = new AccountImpl();
        AccountProxy accountProxy = new AccountProxy(account);
        accountProxy.queryAccount();
        accountProxy.updateAccount();
    }
}
