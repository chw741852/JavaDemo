package com.hong.test.thread.lock;

/**
 * Created by cai on 2015/9/6 17:26.
 */
public class TestLock extends Thread {
    private int no;
    public TestLock(int no) {
        this.no = no;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i < 10; i++) {
            /**
             * ��Ȼrun�����ϼ���synchronized�ؼ��֣����������������½���9��ʵ�������Բ������á�
             * ���Ҫ����Щ�߳̽���ͬ������ô��Щ�߳������еĶ�����Ӧ���ǹ�����Ψһ�ġ�
             */
            new TestLock(i).start();
            Thread.sleep(1);
        }
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println("No." + no + ": " + i);
        }
    }
}
