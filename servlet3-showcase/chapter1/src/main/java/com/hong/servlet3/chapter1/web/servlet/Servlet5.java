package com.hong.servlet3.chapter1.web.servlet;

import com.hong.servlet3.chapter1.web.support.LongRunningProcess;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by caihongwei on 02/01/2018 5:14 PM.
 *
 * 是用线程池来处理异步
 * 使用ServletInputStream处理nio非阻塞
 */
@WebServlet(value = "/servlet5", asyncSupported = true)
public class Servlet5 extends HttpServlet {
    private static final long serialVersionUID = 8963634867830843811L;

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200,
            50000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AsyncContext asyncContext = request.startAsync();
        ServletInputStream inputStream = request.getInputStream();
        inputStream.setReadListener(new ReadListener() {
            @Override
            public void onDataAvailable() throws IOException {

            }
            @Override
            public void onAllDataRead() throws IOException {
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

            @Override
            public void onError(Throwable t) {
                asyncContext.complete();
            }
        });
    }
}
