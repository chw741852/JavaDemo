package com.hong.test.rpc.ex2.support;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * RPC解码
 * Created by Hongwei on 2015/11/12.
 */
public class RpcDecoder extends ByteToMessageDecoder {
    private Class<?> genericClass;

    public RpcDecoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) return;

        in.markReaderIndex();
        int dataLength = in.readInt();
        if (dataLength < 0) ctx.close();
        if (in.readableBytes() < dataLength) in.resetReaderIndex();

        byte[] data = new byte[dataLength];
        in.readBytes(data);
        Object object = SerializationUtil.deserializer(data, genericClass);
        out.add(object);
    }
}
