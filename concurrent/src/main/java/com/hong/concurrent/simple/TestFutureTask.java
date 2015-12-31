package com.hong.concurrent.simple;

import com.hong.concurrent.bean.PersonInfo;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by caihongwei on 2015/12/31 10:54.
 *
 * FutureTask类，主要用于提前处理任务，使用时直接get结果
 */
public class TestFutureTask {
    private final FutureTask<List<PersonInfo>> futureTask = new FutureTask<>(this::loadPersonInfo);

    private List<PersonInfo> loadPersonInfo() {
        List<PersonInfo> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add(new PersonInfo("name_" + i, i + 1));
        }
        return list;
    }

    private final Thread thread = new Thread(futureTask);

    public void start() {
        thread.start();
    }

    public List<PersonInfo> get() throws ExecutionException, InterruptedException {
        return futureTask.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        StopWatch stopWatch = new StopWatch();
        TestFutureTask test = new TestFutureTask();

        stopWatch.start();
        test.start();
        test.get();
        stopWatch.stop();
        System.out.println("耗时：" + stopWatch.getLastTaskTimeMillis());
    }
}
