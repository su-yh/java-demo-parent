package com.suyh5303.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;

@Slf4j
public class ZkStarterListener implements ConnectionStateListener {
    private final CuratorFramework client;

    public ZkStarterListener(CuratorFramework client) {
        this.client = client;
    }

    public void start() {
        // 将当前监听器注册到client 中，并生效。
        client.getConnectionStateListenable().addListener(this);
    }

    @Override
    public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
        if (!connectionState.isConnected()) {
            log.warn("zk disconnection.");
            return;
        }

        log.info("zk connection success.");
    }
}
