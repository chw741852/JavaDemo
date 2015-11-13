package com.hong.test.rpc.ex2.support;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by Hongwei on 2015/11/12.
 */
public class RpcEncoder extends MessageToByteEncoder {
    private Class<?> genericClass;

    public RpcEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (genericClass.isInterface()) {
            byte[] data = SerializationUtil.serializer(msg);
            out.writeInt(data.length);
            out.writeBytes(data);
        }
    }
}
