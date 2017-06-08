package com.hong.concurrent.chapters.c4_3_1;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by caihongwei on 07/06/2017 7:58 PM.
 */
public class Main implements Runnable {
    public static void main(String[] args) {
        Map<String, Point> points = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            Point point = new Point(i, i);
            points.put(String.valueOf(i), point);
        }
        DelegatingVehicleTracker tracker = new DelegatingVehicleTracker(points);

        Main m1 = new Main(tracker, 0);
        Main m2 = new Main(tracker, 1);

        new Thread(m1).start();
        new Thread(m2).start();
    }

    private DelegatingVehicleTracker tracker;
    private int type;

    public Main(DelegatingVehicleTracker tracker, int type) {
        this.tracker = tracker;
        this.type = type;
    }

    @Override
    public void run() {
        if (type == 0) {    // 更新车辆位置
            while (true) {
                String key = String.valueOf(new Random().nextInt(10));
                System.out.println("更新车辆'" + key + "'：" + (tracker.getLocation(key).x + 1) + ", " + (tracker.getLocation(key).y + 1));
                tracker.setLocations(key, tracker.getLocation(key).x + 1, tracker.getLocation(key).y + 1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {    // 获取车辆位置
            while (true) {
                String key = String.valueOf(new Random().nextInt(10));

                System.out.println("车辆'" + key + "'：" + tracker.getLocation(key).x + ", " + tracker.getLocation(key).y);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
