package com.hong.design.observer;

/**
 * 观察者模式
 * Created by Hongwei on 2015/11/24.
 */
public class Test implements IObserser {
    @Override
    public void update(Object... objects) {
        for (Object object : objects) {
            System.out.println(object);
        }
    }

    public static void main(String[] args) {
        Test t = new Test();
        ConcreteObservable.getInstance().registerObserver(t);
        ConcreteObservable.getInstance().notifyObserver(t, "Hello", "World");
    }
}
