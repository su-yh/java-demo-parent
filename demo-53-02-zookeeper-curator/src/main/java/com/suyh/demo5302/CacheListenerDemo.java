package com.suyh.demo5302;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

/**
 * 监听节点
 */
@Slf4j
public class CacheListenerDemo {
    public static CuratorFramework build() {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("docker:2181")
                .namespace("dev/suyh")
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        curatorFramework.start();
        return curatorFramework;
    }

    @Test
    public void test01() throws Exception {
        try {
            String testPath = "pathChildrenCacheTest";
            //创建连接
            CuratorFramework client = CacheListenerDemo.build();
            //如果testPath存在，删除路径
            Stat stat = client.checkExists().forPath("/" + testPath);
            if (stat != null) {
                client.delete().guaranteed().deletingChildrenIfNeeded().forPath("/" + testPath);
            }
            //创建testPath
            client.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/" + testPath, testPath.getBytes());

            //创建PathChildrenCache
            //参数：true代表缓存数据到本地
            PathChildrenCache pathChildrenCache = new PathChildrenCache(client, "/" + testPath, true);
            //BUILD_INITIAL_CACHE 代表使用同步的方式进行缓存初始化。
            pathChildrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
            pathChildrenCache.getListenable().addListener((cf, event) -> {
                PathChildrenCacheEvent.Type eventType = event.getType();
                switch (eventType) {
                    case CONNECTION_RECONNECTED:
                        pathChildrenCache.rebuild();
                        break;
                    case CONNECTION_SUSPENDED:
                        break;
                    case CONNECTION_LOST:
                        System.out.println("Connection lost");
                        break;
                    case CHILD_ADDED:
                        System.out.println("Child added");
                        log.info("Child added, path: {}", event.getData().getPath());
                        break;
                    case CHILD_UPDATED:
                        System.out.println("Child updated");
                        break;
                    case CHILD_REMOVED:
                        System.out.println("Child removed");
                        break;
                    default:
                        System.out.println("other event: " + eventType);
                        break;
                }
            });

            //创建子节点1
            client.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/" + testPath + "/1", testPath.getBytes());
            Thread.sleep(1000);
            //创建子节点1
            client.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/" + testPath + "/2", testPath.getBytes());
            Thread.sleep(1000);
            //删除子节点1
            client.delete().guaranteed().deletingChildrenIfNeeded().forPath("/" + testPath + "/1");
            Thread.sleep(1000);
            //删除子节点2
            client.delete().guaranteed().deletingChildrenIfNeeded().forPath("/" + testPath + "/2");
            Thread.sleep(1000);

            // 可以接收到临时节点的创建事件，但不能监听到更深层次的子节点的创建事件
            client.create().creatingParentContainersIfNeeded()
                    .withMode(CreateMode.EPHEMERAL).forPath("/" + testPath + "/suyh/temp/path-temp", "init".getBytes());

            pathChildrenCache.close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
}
