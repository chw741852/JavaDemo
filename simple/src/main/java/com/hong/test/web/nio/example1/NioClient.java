package com.hong.test.web.nio.example1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by Hongwei on 2015/10/8.
 * NIO 客户端
 */
public class NioClient {
    // 通道管理器
    private Selector selector;

    /**
     * 获得Socket通道，并初始化
     * @param ip
     * @param port
     * @throws IOException
     */
    public void initClient(String ip, int port) throws IOException {
        // 获得一个Socket通道
        SocketChannel channel = SocketChannel.open();
        // 设置通道为非阻塞
        channel.configureBlocking(false);
        // 客户端连接服务器，实际方法并没有实现连接，需要在listen()方法中调用channel.finishConnect()，才能完成连接
        channel.connect(new InetSocketAddress(ip, port));
        // 获得一个通道管理器
        this.selector = Selector.open();
        // 将通道管理器和该通道绑定，并为该通道注册SelectorKey.OP_CONNECT事件
        channel.register(selector, SelectionKey.OP_CONNECT);
    }

    /**
     * 采用轮询方式监听selector上是否有需要处理的事件，如果有，则进行处理
     * @throws IOException
     */
    public void listen() throws IOException {
        // 轮询访问selector
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterable = selector.selectedKeys().iterator();
            while (iterable.hasNext()) {
                SelectionKey key = iterable.next();
                iterable.remove();
                // 连接事件发送
                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 如果正在连接，则完成连接
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                    // 设置成非阻塞
                    channel.configureBlocking(false);
                    // 给服务端发送一条消息
                    channel.write(ByteBuffer.wrap("客户端发送了一条消息".getBytes()));
                    // 在和服务端连接成功后，为了可以接收服务端的信息，需要设置读的权限
                    channel.register(selector, SelectionKey.OP_READ);
                    // 获得了可读的事件
                } else if (key.isReadable()) {
                    read(key);
                }
            }
        }
    }

    /**
     * 处理服务端发来的消息/事件
     * @param key
     * @throws IOException
     */
    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(12);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("客户端收到消息：" + msg);
    }

    public static void main(String[] args) throws IOException {
        NioClient client = new NioClient();
        client.initClient("127.0.0.1", 8000);
        client.listen();
    }
}
