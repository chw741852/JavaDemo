package com.hong.test.java8;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by caihongwei on 06/09/2017 2:44 PM.
 */
public class TestStream {
    private enum Status {
        OPEN, CLOSE
    }
    private static final class Task {
        private final Status status;
        private final int points;

        Task(Status status, int points) {
            this.status = status;
            this.points = points;
        }

        public Status getStatus() {
            return status;
        }

        public int getPoints() {
            return points;
        }

        @Override
        public String toString() {
            return String.format("[%s, %d]", status, points);
        }
    }

    private static final Collection<Task> tasks = Arrays.asList(
            new Task(Status.OPEN, 5),
            new Task(Status.OPEN, 13),
            new Task(Status.CLOSE, 20)
    );

    /**
     * 统计OPEN元素的points总和
     */
    private static void t1() {
        final long totalPointsOfOpen = tasks.stream()
                .filter(task -> task.status == Status.OPEN)
                .mapToInt(Task::getPoints).sum();
        System.out.println(totalPointsOfOpen);
    }

    /**
     * stream 并行处理
     */
    private static void t2() {
        final long totalPoints = tasks.stream()
                .parallel().map(task -> task.getPoints())
                .reduce(0, Integer::sum);
        System.out.println(totalPoints);
    }

    /**
     * 内容按Status分类
     */
    private static void t3() {
        Map<Status, List<Task>> map = tasks.stream()
                .collect(Collectors.groupingBy(Task::getStatus));
        System.out.println(map);
    }

    /**
     * 计算整个集合中每个task分数（或权重）的平均值
     */
    private static void t4() {
        final long totalPoints = tasks.stream().mapToLong(Task::getPoints).sum();
        final Collection<Double> ps = tasks.stream()
                .mapToInt(Task::getPoints)
                .asDoubleStream()
                .map(p -> p / totalPoints)
                .boxed()
                .collect(Collectors.toList());
        final Collection<String> result = tasks.stream()
                .mapToInt(Task::getPoints)
                .asDoubleStream()
                .map(points -> points / totalPoints)
                .boxed()
                .mapToLong(weight -> (long) (weight * 100))
                .mapToObj(percentage -> percentage + "%")
                .collect(Collectors.toList());
        System.out.println(totalPoints);
        System.out.println(ps);
        System.out.println(result);
    }

    private static void t5() {
        final String filename = "/Users/caihongwei/Documents/test.txt";
        final Path path = new File(filename).toPath();
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            lines.onClose(() -> System.out.println("Done.")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        t1();
        t2();
        t3();
        t4();
        t5();
    }
}
