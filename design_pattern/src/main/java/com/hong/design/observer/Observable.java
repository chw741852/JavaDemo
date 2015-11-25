package com.hong.design.observer;

import jdk.nashorn.internal.AssertsEnabled;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 被观察者
 * Created by Hongwei on 2015/11/24.
 */
public abstract class Observable {
    public final List<Class<?>> obserList = new ArrayList<>();

    /**
     * 通过实例注册观察者
     * @param ob 实例
     * @param <T>
     */
    public <T> void registerObserver(T ob) {
        if (ob == null) throw new NullPointerException();
        registerObserver(ob.getClass());
    }

    /**
     * 通过class注册观察者
     * @param cls
     */
    public void registerObserver(Class<?> cls) {
        if (cls == null) throw new NullPointerException();
        synchronized (obserList) {
            if (!obserList.contains(cls))
                obserList.add(cls);
        }
    }

    /**
     * 注销观察者
     * @param ob 实例
     * @param <T>
     */
    public <T> void unRegisterObserver(T ob) {
        if (ob == null) throw new NullPointerException();
        unRegisterObserver(ob.getClass());
    }

    /**
     * 注销观察者
     * @param cls
     */
    public void unRegisterObserver(Class<?> cls) {
        if (cls == null) throw new NullPointerException();
        synchronized (obserList) {
            Iterator<Class<?>> iterator = obserList.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getName().equals(cls.getName())) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    public void unRegisterAll() {
        synchronized (obserList) {
            obserList.clear();
        }
    }

    public int countObservers() {
        synchronized (obserList) {
            return obserList.size();
        }
    }

    /**
     * 通知所有观察者
     * @param objects
     */
    public abstract void notifyObservers(Object...objects);

    /**
     * 通知某一个确定观察者
     * @param t
     * @param objects
     * @param <T>
     */
    public abstract<T> void notifyObserver(T t, Object...objects);

    /**
     * 通知某一个确定观察者
     * @param cls
     * @param objects
     */
    public abstract void notifyObserver(Class<?> cls, Object...objects);
}
