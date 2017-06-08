package com.hong.concurrent.chapters.c4_3_1;

import com.hong.concurrent.annotation.Immutable;

/**
 * Created by caihongwei on 07/06/2017 7:40 PM.
 */
@Immutable
public class Point {
    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
