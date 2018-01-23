package com.hong.concurrent;

import java.util.concurrent.*;

/**
 * Created by caihongwei on 23/01/2018 11:36 AM.
 *
 * CyclicBarriar的一个特别典型的应用场景是：
 * 有一个比较大型的任务，需要分配好多个人分多个阶段去执行，在每个阶段，需要每个人都参与，
 * 并且需要所有人在完成各自的子任务后才算完成这个阶段的工作，才能开始下一个阶段的子任务，
 * 最后所有阶段工作都完成后，才能执行主任务，这时候，就可以选择CyclicBarrier了。
 */
public class CyclicBarrierDemo {
    private static final int AMOUNT = 3;    // 等待人数

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(AMOUNT);
        final CyclicBarrier cb = new CyclicBarrier(AMOUNT);
        MyRunnable myRunnable1 = new MyRunnable("张三", cb);
        exe.execute(myRunnable1);
        MyRunnable myRunnable2 = new MyRunnable("李四", cb);
        exe.execute(myRunnable2);
        MyRunnable myRunnable3 = new MyRunnable("王五", cb);
        exe.execute(myRunnable3);

        exe.shutdown();
    }

    static class MyRunnable implements Runnable {
        private String name;
        private CyclicBarrier cb;

        public MyRunnable(String name, CyclicBarrier cb) {
            this.name = name;
            this.cb = cb;
        }

        public void reachSchool(String name, CyclicBarrier cb) {
            try {
                Thread.sleep((long) (Math.random() * 100));
                System.out.println(name + "到达学校，当前共" + (cb.getNumberWaiting() + 1) + "到达" +
                        (cb.getNumberWaiting() == (AMOUNT-1) ? "，已全部到达，向公园出发。" : "，继续等待。"));

                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        public void reachPark(String name, CyclicBarrier cb) {
            try {
                Thread.sleep((long) (Math.random() * 100));
                System.out.println(name + "到达公园，当前共" + (cb.getNumberWaiting() + 1) + "到达" +
                        (cb.getNumberWaiting() == (AMOUNT-1) ? "，已全部到达，向酒店出发。" : "，继续等待。"));

                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        public void reachHotel(String name, CyclicBarrier cb) {
            try {
                Thread.sleep((long) (Math.random() * 100));
                System.out.println(name + "到达酒店，当前共" + (cb.getNumberWaiting() + 1) + "到达" +
                        (cb.getNumberWaiting() == (AMOUNT-1) ? "，已全部到达，开始吃饭。" : "，继续等待。"));

                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            reachSchool(name, cb);

            reachPark(name, cb);

            reachHotel(name, cb);
        }
    }
}
