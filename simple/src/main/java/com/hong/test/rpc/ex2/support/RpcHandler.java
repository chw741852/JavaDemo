package com.hong.test.rpc.ex2.support;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

import java.util.Map;

/**
 * 请求处理器
 *
 * Created by Hongwei on 2015/11/12.
 */
public class RpcHandler extends SimpleChannelInboundHandler<RpcRequest> {
    private final Map<String, Object> handlerMap;

    public RpcHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    /**
     * 请求处理主体
     * @param request
     * @return
     * @throws Throwable
     */
    private Object handle(RpcRequest request) throws Throwable {
        String className = request.getClassName();
        Object serviceBean = handlerMap.get(className);
        Class<?> serviceClass = serviceBean.getClass();
        String methodName = request.getMethodName();
        Class<?>[] paramsterTypes = request.getParameterTypes();
        Object[] params = request.getParams();
        FastClass serviceFastClass = FastClass.create(serviceClass);
        FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, paramsterTypes);
        return serviceFastMethod.invoke(serviceBean, params);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, RpcRequest request) throws Exception {
        RpcResponse response = new RpcResponse();
        response.setRequestId(request.getRequestId());
        System.out.println("处理请求：" + request);
        Object result;
        try {
            result = handle(request);
            response.setResult(result);
        } catch (Throwable throwable) {
            response.setError(throwable);
        }
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
