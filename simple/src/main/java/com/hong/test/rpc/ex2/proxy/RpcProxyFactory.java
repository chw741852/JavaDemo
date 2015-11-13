package com.hong.test.rpc.ex2.proxy;

import com.hong.test.rpc.ex2.client.RpcClient;
import com.hong.test.rpc.ex2.server.ServiceDiscovery;
import com.hong.test.rpc.ex2.support.RpcRequest;
import com.hong.test.rpc.ex2.support.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * Created by Hongwei on 2015/11/13.
 */
public class RpcProxyFactory {
    private static final Logger logger = LoggerFactory.getLogger(RpcProxyFactory.class);
    private String serverAddress;
    private ServiceDiscovery serviceDiscovery;

    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                logger.debug("创建代理请求：{0}", method.getName());
                RpcRequest request = new RpcRequest();
                request.setRequestId(UUID.randomUUID().toString());
                request.setClassName(method.getDeclaringClass().getName());
                request.setMethodName(method.getName());
                request.setParameterTypes(method.getParameterTypes());
                request.setParams(args);
                // 如果服务发现不为空则主动发现服务，否则使用默认服务地址
                if (serviceDiscovery != null) {
                    serverAddress = serviceDiscovery.discovery();   // 发现服务
                }
                // 服务的地址是host:port
                String[] array = serverAddress.split(":");
                String host = array[0];
                int port = Integer.parseInt(array[1]);

                RpcClient client = new RpcClient(host, port);
                RpcResponse response = client.send(request);

                if (response.isError()) {
                    throw response.getError();
                } else {
                    return response.getResult();
                }
            }
        });
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public ServiceDiscovery getServiceDiscovery() {
        return serviceDiscovery;
    }

    public void setServiceDiscovery(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }
}
