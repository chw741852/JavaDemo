package com.hong.test.thread;

public class ProduceConsume {

    public static void main(String[] args) {
        SyncStack ss = new SyncStack();//建造一个装馒头的框  
        Producer p = new Producer(ss);//新建一个生产者，使之持有框  
        Consume c = new Consume(ss);//新建一个消费者，使之持有同一个框  
        Thread tp = new Thread(p);//新建一个生产者线程  
        Thread tc = new Thread(c);//新建一个消费者线程  
        tp.start();//启动生产者线程  
        tc.start();//启动消费者线程  
    }

}

//馒头类  
class SteamBread {
    int id;//馒头编号  

    SteamBread(int id) {
        this.id = id;
    }

    public String toString() {
        return "steamBread:" + id;
    }
}

//装馒头的框，栈结构  
class SyncStack {
    int index = 0;
    SteamBread[] stb = new SteamBread[6];//构造馒头数组，相当于馒头筐，容量是6  

    //放入框中，相当于入栈  
    public synchronized void push(SteamBread sb) {
        while (index == stb.length) {//筐满了，即栈满，
            try {
                this.wait();//让当前线程等待  
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();//唤醒在此对象监视器上等待的单个线程，即消费者线程  
        stb[index] = sb;
        this.index++;
    }

    //从框中拿出，相当于出栈  
    public synchronized SteamBread pop() {
        while (index == 0) {//筐空了，即栈空
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        this.index--;//push第n个之后，this.index++，使栈顶为n+1，故return之前要减一  
        return stb[index];
    }
}

//生产者类，实现了Runnable接口，以便于构造生产者线程  
class Producer implements Runnable {
    SyncStack ss = null;

    Producer(SyncStack ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        // 开始生产馒头  
        for (int i = 0; i < 20; i++) {
            SteamBread stb = new SteamBread(i);
            ss.push(stb);
            System.out.println("生产了" + stb);
            try {
                Thread.sleep(10);//每生产一个馒头，睡觉10毫秒  
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//消费者类，实现了Runnable接口，以便于构造消费者线程  
class Consume implements Runnable {
    SyncStack ss = null;

    public Consume(SyncStack ss) {
        super();
        this.ss = ss;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {//开始消费馒头
            SteamBread stb = ss.pop();
            System.out.println("消费了" + stb);
            try {
                Thread.sleep(100);//每消费一个馒头，睡觉100毫秒。即生产多个，消费一个
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
