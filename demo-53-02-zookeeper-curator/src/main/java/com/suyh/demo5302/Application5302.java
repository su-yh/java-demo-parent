package com.suyh.demo5302;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

/**
 * 参考博客：https://www.jianshu.com/p/70151fc0ef5d/
 */
@Slf4j
public class Application5302 {
    public static void main(String[] args) {
    }

    /**
     * newClient静态工厂方法包含四个主要参数：
     * <p>
     * connectionString 服务器列表，格式host1:port1,host2:port2,...
     * retryPolicy 重试策略,内建有四种重试策略,也可以自行实现RetryPolicy接口
     * sessionTimeoutMs 会话超时时间，单位毫秒，默认60000ms
     * connectionTimeoutMs 连接创建超时时间，单位毫秒，默认60000ms
     */
    @Test
    public void demo01() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                "docker:2181", 5000, 3000, retryPolicy);

        client.start();
    }

    @Test
    public void demo02() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("docker:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();

        client.start();
    }

    /**
     * 为了实现不同的Zookeeper业务之间的隔离，需要为每个业务分配一个独立的命名空间（NameSpace），
     * 即指定一个Zookeeper的根路径（官方术语：为Zookeeper添加“Chroot”特性）。
     * 例如（下面的例子）当客户端指定了独立命名空间为“/base”，那么该客户端对Zookeeper上的数据节点的操作都是基于该目录进行的。
     * 通过设置Chroot可以将客户端应用与Zookeeper服务端的一课子树相对应，在多个应用共用一个Zookeeper集群的场景下，
     * 这对于实现不同应用之间的相互隔离十分有意义。
     */
    @Test
    public void demo03() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("docker:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("base")
                .build();

        client.start();

        CuratorListener listener = (cli, event) -> {
            switch (event.getType()) {
                case CREATE:
                    log.info("create event");
                    break;
                case DELETE:
                    log.info("delete event");
                    break;
                default:
                    break;
            }
        };
        client.getCuratorListenable().addListener(listener);



        // 节点存在是创建不成功的
        // 默认情况下创建的节点是持久化节点。
        client.create().forPath("/path");

        // 删除一个节点
        // 注意，此方法只能删除叶子节点，否则会抛出异常。
        client.delete().forPath("/path");

        // 创建节点，同时带初始化内容
        client.create().forPath("/path", "init".getBytes());
        client.delete().forPath("/path");


        // 创建一个节点，指定创建模式（临时节点），附带初始化内容，并且自动递归创建父节点。
        // 但是这样创建出来的父节点是持久节点
        client.create().creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath("/suyh/temp/path-temp", "init".getBytes());
        client.delete().forPath("/suyh/temp/path-temp");

        // 创建临时节点，父节点(/suyh/temp) 必须存在，否则创建失败
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/suyh/temp/path-temp");

        // 删除一个节点，并且递归删除其所有的子节点
        client.delete().deletingChildrenIfNeeded().forPath("/suyh/temp");

        // 删除一个节点，强制指定版本进行删除
        // client.delete().withVersion(10086).forPath("/suyh");

        // 删除一个节点，强制保证删除
        client.delete().guaranteed().forPath("/suyh");


    }
}
