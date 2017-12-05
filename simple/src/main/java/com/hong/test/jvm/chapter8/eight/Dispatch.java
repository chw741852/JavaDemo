package com.hong.test.jvm.chapter8.eight;

/**
 * Created by caihongwei on 04/12/2017 7:56 PM.
 */
public class Dispatch {
    static class QQ {

    }
    static class _360 {

    }

    public static class Father {
        public void chooseQQ(QQ qq) {
            System.out.println("father choose qq");
        }

        public void choose360(_360 _3) {
            System.out.println("father choose 360");
        }
    }

    public static class Son extends Father {
        public void chooseQQ(QQ qq) {
            System.out.println("son choose qq");
        }

        public void choose360(_360 _3) {
            System.out.println("son choose 360");
        }
    }

    public static void main(String[] args) {
        Father father = new Father();
        father.chooseQQ(new QQ());

        Son son = new Son();
        son.choose360(new _360());
    }
}
