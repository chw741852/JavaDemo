package com.hong.test.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理代理类
 *
 * jkd的动态代理依靠接口实现，如果有些类并没有实现接口，则不能使用JDK代理，这就要使用cglib动态代理了。
 *
 * Created by Hongwei on 2015/10/27.
 */
public class JdkProxy implements InvocationHandler {
    private Object target;

    /**
     * 绑定委托对象并返回一个代理类
     * @param target
     * @return
     */
    public Object bind(Object target) {
        this.target = target;

        // 取得代理对象
        // 要绑定接口（这是一个缺陷，cglib弥补了这一缺陷）
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     * 调用方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        System.out.println("事务开始");
        // 执行方法
        result = method.invoke(target, args);
        System.out.println("事务结束");

        return result;
    }
}
