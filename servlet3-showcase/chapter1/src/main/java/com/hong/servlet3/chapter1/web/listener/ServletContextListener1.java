package com.hong.servlet3.chapter1.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by caihongwei on 29/12/2017 4:25 PM.
 */
@WebListener
public class ServletContextListener1 implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("init servlet context");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("destroy servlet context");
    }
}
