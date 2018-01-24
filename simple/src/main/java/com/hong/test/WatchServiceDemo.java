package com.hong.test;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by caihongwei on 24/01/2018 4:43 PM.
 *
 * 文件监听器
 */
public class WatchServiceDemo {
    public static void main(String[] args) throws IOException {
        String filename = "test.properties";
        Resource resource = new ClassPathResource(filename);
        final Properties[] properties = {PropertiesLoaderUtils.loadProperties(resource)};
        WatchService watchService = FileSystems.getDefault().newWatchService();
        // 注册修改与删除监听
        Paths.get(resource.getFile().getParent())
                .register(watchService, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);

        while (true) {
            try {
                WatchKey watchKey = watchService.take();    // 此处阻塞；更新文件也没反应，只有当再启动一个JVM时，触发程序继续执行
                for (WatchEvent<?> watchEvent : watchKey.pollEvents()) {
                    if (Objects.equals(watchEvent.context().toString(), filename)) {
                        properties[0] = PropertiesLoaderUtils.loadProperties(resource);
                        System.out.println(filename + " 更新");
                        break;
                    }
                }
                boolean v = watchKey.reset();
                if (!v) {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


//         JVM关闭时，关闭watchService
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            try {
//                watchService.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }));
    }
}
