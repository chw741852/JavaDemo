package com.hong.test.rpc.ex2.register;

import com.hong.test.rpc.ex2.constant.Constant;
import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * RPC服务注册
 * <ul>
 *     <li>注册方法是register()，改方法的主要功能如下：</li>
 *     <li>对目标服务器创建一个zookeeper实例</li>
 *     <li>如果可以创建ZooKeeper实例，则创建一个节点</li>
 * </ul>
 * Created by Hongwei on 2015/11/12.
 */
public class ServiceRegistry {
    private static final Logger logger = LoggerFactory.getLogger(ServiceRegistry.class);
    // 使用计数器实现同步
    private CountDownLatch latch = new CountDownLatch(1);
    private int timeout = Constant.DEFAULT_ZK_SESSION_TIMEOUT;
    private String registerPath = Constant.DEFAULT_ZK_REGISTRY_PATH;
    private String registerAddress;

    public void register(String data) {
        logger.info("注册服务：{}", data);
        if (data != null) {
            ZooKeeper zk = connectServer();
            if (zk != null)
                // 创建节点
                createNode(zk, data);
        }
    }

    /**
     * 创建节点
     * @param zk
     * @param data 注册数据
     */
    private void createNode(ZooKeeper zk, String data) {
        byte[] bytes = data.getBytes();

        try {
            /*
             * 节点路径
             * 初始化数据
             * 节点的acl
             * 指定节点创建策略
             */
            String createResult = zk.create(registerPath, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            logger.info("创建结果：" + createResult);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接ZooKeeper
     * @return
     */
    private ZooKeeper connectServer() {
        ZooKeeper zk = null;

        try {
            logger.info("创建zk，参数是：address: " + registerAddress + "，timeout: " + timeout);
            zk = new ZooKeeper(registerAddress, timeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                        // 计数器减一
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return zk;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getRegisterPath() {
        return registerPath;
    }

    public void setRegisterPath(String registerPath) {
        this.registerPath = registerPath;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }
}
