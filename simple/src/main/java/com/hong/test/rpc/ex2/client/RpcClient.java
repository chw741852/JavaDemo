package com.hong.test.rpc.ex2.client;

import com.hong.test.rpc.ex2.support.RpcDecoder;
import com.hong.test.rpc.ex2.support.RpcEncoder;
import com.hong.test.rpc.ex2.support.RpcRequest;
import com.hong.test.rpc.ex2.support.RpcResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by Hongwei on 2015/11/12.
 */
public class RpcClient extends SimpleChannelInboundHandler<RpcResponse> {
    private String host;
    private int port;
    private RpcResponse response;
    private final Object obj = new Object();

    public RpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponse response) throws Exception {
        this.response = response;
        synchronized (obj) {
            obj.notifyAll();    // 收到响应，唤醒线程
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    public RpcResponse send(RpcRequest request) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new RpcEncoder(RpcRequest.class)) // 将RPC请求进行编码
                        .addLast(new RpcDecoder(RpcResponse.class)) // 将RPC响应进行解码
                        .addLast(RpcClient.this);   // 使用RpcClient发送RPC请求
                    }
                }).option(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = bootstrap.connect(host, port).sync();
            future.channel().writeAndFlush(request).sync();
            synchronized (obj) {
                obj.wait(); // 未收到响应，使线程等待
            }
            if (response != null) {
                future.channel().closeFuture().sync();
            }
            return response;
        } finally {
            group.shutdownGracefully();
        }

    }
}
