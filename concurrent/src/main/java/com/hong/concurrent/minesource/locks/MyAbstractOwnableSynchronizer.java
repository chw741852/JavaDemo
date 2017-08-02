package com.hong.concurrent.minesource.locks;

import java.io.Serializable;

/**
 * Created by caihongwei on 02/08/2017 11:39 AM.
 */
public abstract class MyAbstractOwnableSynchronizer implements Serializable {
    private static final long serialVersionUID = 4409271977582482306L;

    protected MyAbstractOwnableSynchronizer() { }

    private transient Thread exclusiveOwnerThread;

    protected final void setExclusiveOwnerThread(Thread thread) {
        exclusiveOwnerThread = thread;
    }

    protected final Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }
}
