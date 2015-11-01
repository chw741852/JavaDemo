package com.hong.test.web.nio.example1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by Hongwei on 2015/10/8.
 * nio通信，服务端
 */
public class NioServer {
    // 通道管理器
    private Selector selector;

    /**
     * 初始化ServerSocket通道
     * @param port 绑定端口
     * @throws IOException
     */
    public void initServer(int port) throws IOException {
        // 获得一个ServerSocket通道
        ServerSocketChannel channel = ServerSocketChannel.open();
        // 设置通道为非阻塞
        channel.configureBlocking(false);
        // 将该通道对应的ServerSocket绑定到port端口
        channel.socket().bind(new InetSocketAddress(port));
        // 获得一个通道管理
        this.selector = Selector.open();
        /*
         * 将该通道管理器和通道绑定，并为通道注册SelectionKey.OP_ACCEPT时间；
         * 当事件到达时，selector.select()会返回，如果事件未到达，selector.select()会一直阻塞
         */
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 采用轮询的方式监听selector上是否需要处理的事件，如果有，则进行处理
     * @throws IOException
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public void listen() throws IOException {
        System.out.println("服务器启动成功");
        // 轮询访问selector
        while (true) {
            // 当注册事件到达时返回，否则一直阻塞
            selector.select();
            // 获得selector中选中的项的迭代器，选中的项为注册的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 删除已选的key，防止重复处理
                iterator.remove();
                // 客户端请求连接事项
                if (key.isAcceptable()) {
                    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                    // 获得和客户端的连接通道
                    SocketChannel channel = serverChannel.accept();
                    // 设置成非阻塞
                    channel.configureBlocking(false);
                    // 给客户端发送一条消息
                    channel.write(ByteBuffer.wrap("服务器连接成功".getBytes()));
                    // 和连接成功后，为了可以接收客户端的信息，需要给通道设置读权限
                    channel.register(selector, SelectionKey.OP_READ);

                    // 获得了可读事件
                } else if (key.isReadable()){
                    read(key);
                }
            }
        }
    }

    /**
     * 处理读取客户端发来的信息/事件
     * @param key
     * @throws IOException
     */
    private void read(SelectionKey key) throws IOException{
        // 服务器可读消息：得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        // FIXME 创建读取的缓冲区，一个中文占4个字节，读入大文件时，可能因为字符分割问题出现乱码
        ByteBuffer buffer = ByteBuffer.allocate(12);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("服务端收到消息：" + msg);
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
        channel.write(outBuffer);   // 将消息回送给客户端
    }

    public static void main(String[] args) throws IOException {
        NioServer nioServer = new NioServer();
        nioServer.initServer(8000);
        nioServer.listen();
    }
}
