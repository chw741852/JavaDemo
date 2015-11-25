package com.hong.design.observer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Hongwei on 2015/11/24.
 */
public class ConcreteObservable extends Observable {
    private static ConcreteObservable instance = null;
    private ConcreteObservable(){}
    public static synchronized ConcreteObservable getInstance() {
        if (instance == null)
            instance = new ConcreteObservable();
        return instance;
    }

    @Override
    public void notifyObservers(Object... objects) {
        for (Class<?> clz : obserList) {
            notifyObserver(clz, objects);
        }
    }

    @Override
    public <T> void notifyObserver(T t, Object... objects) {
        if (t == null) throw new NullPointerException();
        notifyObserver(t.getClass(), objects);
    }

    @Override
    public void notifyObserver(Class<?> cls, Object... objects) {
        if (cls == null) throw new NullPointerException();
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("update")) {
                try {
                    method.invoke(cls.newInstance(), (Object) objects);
                    break;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
