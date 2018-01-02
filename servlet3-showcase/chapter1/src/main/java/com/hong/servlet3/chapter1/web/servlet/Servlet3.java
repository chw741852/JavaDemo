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
 * Created by caihongwei on 02/01/2018 5:11 PM.
 *
 * 自己手动创建新线程一般是不被鼓励的，并且此时线程不能重用。
 * 因此，一种更好的办法是我们自己维护一个线程池
 */
@WebServlet(value = "/servlet3", asyncSupported = true)
public class Servlet3 extends HttpServlet {
    private static final long serialVersionUID = -6744985270182540669L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AsyncContext asyncContext = request.startAsync();

        Runnable runnable = () -> {
            new LongRunningProcess().run();
            try {
                asyncContext.getResponse().getWriter().write("Hello World!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            asyncContext.complete();
        };

        new Thread(runnable).start();
    }
}
