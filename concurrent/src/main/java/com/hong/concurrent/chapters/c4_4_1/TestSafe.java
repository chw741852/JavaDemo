package com.hong.concurrent.chapters.c4_4_1;

/**
 * Created by caihongwei on 07/06/2017 7:58 PM.
 */
public class TestSafe implements Runnable {
    public static void main(String[] args) {
        ListHelper2 listHelper = new ListHelper2();

        TestSafe m1 = new TestSafe(listHelper, 0);
        TestSafe m2 = new TestSafe(listHelper, 1);

        new Thread(m1).start();
        new Thread(m2).start();

        // 当此处add 0执行时，可以锁住线程m1 or m2
        /*
         * list add 0: true
         * list0 add '0': false
         * list1 add '0': false
         */
        listHelper.list.add(0);
        System.out.println("list add 0: true");
    }

    private ListHelper2 listHelper;
    private int type;

    public TestSafe(ListHelper2 listHelper, int type) {
        this.listHelper = listHelper;
        this.type = type;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            boolean r = listHelper.putIfAbsend(i);
            System.out.println("list" + type + " add '" + i + "': " + r);
        }
    }
}
