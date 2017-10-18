package com.hong.concurrent.chapters.c4_4_1;

/**
 * Created by caihongwei on 07/06/2017 7:58 PM.
 */
public class TestNotSafe implements Runnable {
    public static void main(String[] args) {
        ListHelper<Integer> listHelper = new ListHelper<>();

        TestNotSafe m1 = new TestNotSafe(listHelper, 0);
        TestNotSafe m2 = new TestNotSafe(listHelper, 1);

        new Thread(m1).start();
        new Thread(m2).start();

        // 当此处add 0执行时，并不能锁住线程m1 or m2
        /*
         * list add 0: true
         * list0 add '0': true
         * list1 add '0': false
         */
        listHelper.list.add(0);
        System.out.println("list add 0: true");
    }

    private ListHelper listHelper;
    private int type;

    public TestNotSafe(ListHelper listHelper, int type) {
        this.listHelper = listHelper;
        this.type = type;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            boolean r = listHelper.putIfAbsend(i);
            System.out.println("list" + type + " add '" + i + "': " + r);
        }
    }
}
