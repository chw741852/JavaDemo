package com.hong.concurrent.chapters.c4_4_1;

import com.hong.concurrent.annotation.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by caihongwei on 08/06/2017 4:48 PM.
 */
@ThreadSafe
public class ListHelper2<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public boolean putIfAbsend(E e) {
        synchronized (list) {
            boolean absent = !list.contains(e);
            if (absent)
                list.add(e);
            return absent;
        }
    }
}
