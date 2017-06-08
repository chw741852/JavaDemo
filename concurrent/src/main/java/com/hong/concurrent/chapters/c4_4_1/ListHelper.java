package com.hong.concurrent.chapters.c4_4_1;

import com.hong.concurrent.annotation.NotThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by caihongwei on 08/06/2017 4:24 PM.
 */
@NotThreadSafe
public class ListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    // 此处的锁，跟list.add的锁并非是同一个
    public synchronized boolean putIfAbsend(E e) {
        boolean absent = !list.contains(e);
        if (absent)
            list.add(e);
        return absent;
    }
}
