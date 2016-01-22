package com.hong.test.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by caihongwei on 2016/1/22 10:12.
 */
public class HelloWorld {
    private static final Logger logger = LogManager.getLogger("HelloWorld");

    public static void main(String[] args) {
        logger.info("Hello World");
    }
}
