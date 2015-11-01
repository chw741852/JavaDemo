package com.hong.test.proxy.dynamic.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib是针对类来实现代理的
 * 他的原理是对指定的目标类生成一个子类，并覆盖其中方法实现增强，
 * 但因为采用的是继承，所以不能对final修饰的类进行代理。
 *
 * Created by Hongwei on 2015/10/27.
 */
public class CglibProxy implements MethodInterceptor {
    private Object target;

    /**
     * 创建代理对象
     * @param target
     * @return
     */
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    // 回调方法
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("事务开始");
        Object result = methodProxy.invokeSuper(o, args);
        System.out.println("事务结束");

        return result;
    }
}
