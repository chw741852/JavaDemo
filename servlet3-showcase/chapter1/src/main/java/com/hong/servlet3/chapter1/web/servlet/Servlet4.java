package com.hong.servlet3.chapter1.web.servlet;

import com.hong.servlet3.chapter1.web.support.LongRunningProcess;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by caihongwei on 02/01/2018 5:13 PM.
 *
 * 使用线程池来异步处理
 */
@WebServlet(value = "/servlet4", asyncSupported = true)
public class Servlet4 extends HttpServlet {
    private static final long serialVersionUID = -2948138695590994557L;

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 50000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AsyncContext asyncContext = request.startAsync();
        executor.execute(() -> {
            new LongRunningProcess().run();
            try {
                asyncContext.getResponse().getWriter().write("Hello World!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            asyncContext.complete();
        });
    }
}
