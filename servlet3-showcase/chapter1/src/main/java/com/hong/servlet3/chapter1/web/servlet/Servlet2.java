package com.hong.servlet3.chapter1.web.servlet;

import com.hong.servlet3.chapter1.web.support.LongRunningProcess;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by caihongwei on 02/01/2018 5:05 PM.
 *
 * 异步处理
 * 此时会返回主线程，新获取一个线程来处理（可以是从Servlet容器中已有的主线程池获取，也可以另外维护一个线程池，不同容器实现可能不一样）
 */
@WebServlet(value = "/servlet2", asyncSupported = true)
public class Servlet2 extends HttpServlet {
    private static final long serialVersionUID = 7328191859868247504L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AsyncContext asyncContext = request.startAsync();

        asyncContext.start(() -> {
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
