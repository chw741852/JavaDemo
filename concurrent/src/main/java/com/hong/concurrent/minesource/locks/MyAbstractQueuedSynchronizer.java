package com.hong.concurrent.minesource.locks;

/**
 * Created by caihongwei on 02/08/2017 4:24 PM.
 */
public abstract class MyAbstractQueuedSynchronizer extends MyAbstractOwnableSynchronizer {

    private static final long serialVersionUID = 4895992043409262615L;

    protected MyAbstractQueuedSynchronizer() { }

    static final class Node {
        static final Node SHARED = new Node();
        static final Node EXCLUSIVE = new Node();

        static final int CANCELLED = 1;
        static final int SIGNAL = -1;
        static final int CONDITION = -2;
        static final int PROPAGATE = -3;

        volatile int waitStatus;

        volatile Node prev;
        volatile Node next;

        volatile Thread thread;

        Node nextWaiter;

        final boolean isShared() {
            return nextWaiter == SHARED;
        }

        final Node predecessor() throws NullPointerException {
            Node p = prev;
            if (p == null) {
                throw new NullPointerException();
            } else {
                return p;
            }
        }

        Node() { }

        Node(Thread thread, Node mode) {
            this.nextWaiter = mode;
            this.thread = thread;
        }

        Node(Thread thread, int waitStatus) {
            this.thread = thread;
            this.waitStatus = waitStatus;
        }
    }

    private transient volatile Node head;
    private transient volatile Node tail;

    private volatile int state;

    protected final int getState() {
        return state;
    }
    protected final void setState(int newState) {
        state = newState;
    }

//    protected final boolean compareAndSetState(int expect, int update) {
//        return unsafe
//    }
}
