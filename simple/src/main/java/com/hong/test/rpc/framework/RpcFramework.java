package com.hong.test.rpc.framework;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * rpc framework
 *
 * Created by Hongwei on 2015/10/30.
 */
public class RpcFramework {
    /**
     * 暴露服务
     * @param service 服务实现
     * @param port 服务端口
     * @throws Exception
     */
    public static void export(final Object service, int port) throws Exception {
        if (service == null)
            throw new IllegalArgumentException("service instance is null");
        if (port <= 0 || port > 65535)
            throw new IllegalArgumentException("invalid port: " + port);
        System.out.println("Export service " + service.getClass().getName() + " on port " + port);

        ServerSocket serverSocket = new ServerSocket(port);
        //noinspection InfiniteLoopStatement
        for (;;) {
            final Socket socket = serverSocket.accept();
            try {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            try {
                                try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
                                    String methodName = inputStream.readUTF();
                                    Class<?>[] parameterTypes = (Class<?>[]) inputStream.readObject();
                                    Object[] arguments = (Object[]) inputStream.readObject();
                                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                                    try {
                                        Method method = service.getClass().getMethod(methodName, parameterTypes);
                                        Object result = method.invoke(service, arguments);
                                        outputStream.writeObject(result);
                                    } catch (Throwable t) {
                                        outputStream.writeObject(t);
                                    } finally {
                                        outputStream.close();
                                    }
                                }
                            } finally {
                                socket.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 引用服务
     * @param interfaceClass 接口类型
     * @param host 服务器主机名
     * @param port 服务器端口
     * @param <T> 接口泛型
     * @return 远程服务
     * @throws Exception
     */
    public static <T> T refer (final Class<T> interfaceClass, final String host, final int port) throws Exception {
        if (interfaceClass == null)
            throw new IllegalArgumentException("interface class is null");
        if (!interfaceClass.isInterface())
            throw new IllegalArgumentException("The " + interfaceClass.getName() + " must be interface class!");
        if (host == null || host.length() == 0)
            throw new IllegalArgumentException("host is null");
        if (port <= 0 || port > 65535)
            throw new IllegalArgumentException("invalid port: " + port);
        System.out.println("Get remote service " + interfaceClass.getName() + " from server " + host + ":" + port);

        //noinspection unchecked
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("invoke.........");
                try (Socket socket = new Socket(host, port)) {
                    try (ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {
                        outputStream.writeUTF(method.getName());
                        outputStream.writeObject(method.getParameterTypes());
                        outputStream.writeObject(args);
                        try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
                            Object result = inputStream.readObject();
                            if (result instanceof Throwable)
                                throw (Throwable) result;
                            return result;
                        }
                    }
                }
            }
        });
    }
}
