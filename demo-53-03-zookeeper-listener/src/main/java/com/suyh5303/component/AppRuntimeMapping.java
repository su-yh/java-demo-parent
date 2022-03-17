
package com.suyh5303.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class AppRuntimeMapping implements CuratorCacheListener, InitializingBean {
    public static final String LISTENER_PATH = "/mapping/app-instance";
    private final CuratorFramework client;

    // key: app name, value: arms runtime instance ip:port
    private final Map<String, String> mapping = new ConcurrentHashMap<>();

    public AppRuntimeMapping(CuratorFramework client) {
        this.client = client;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        start();
    }

    public void start() {
        // 对指定路进行监听器的创建，每一个路径对应一个监听器
        CuratorCache curatorCache = CuratorCache.build(client, LISTENER_PATH);
        // 注册监听器
        curatorCache.listenable().addListener(this);
        // 开启监听器
        curatorCache.start();
    }

    @Override
    public void event(Type type, ChildData oldData, ChildData data) {
        switch (type) {
            case NODE_CREATED: {
                log.info("create event, oldData: {}, newData: {}", oldData, data);
                if (data == null) {
                    break;
                }
                String path = data.getPath();
                if (LISTENER_PATH.equals(path)) {
                    break;
                }
                String appName = path.substring(LISTENER_PATH.length() + 1);
                if (appName.indexOf("/") > 0) {
                    log.error("illegal resource name: {}", appName);
                    break;
                }
                String runtimeInstance = new String(data.getData());
                log.info("path: {}, application name: {}, data: {}", path, appName, runtimeInstance);
                mapping.put(appName, runtimeInstance);
            }
            break;
            case NODE_DELETED: {
                log.info("delete event, oldData: {}, newData: {}", oldData, data);
                if (oldData == null) {
                    break;
                }
                String path = oldData.getPath();
                if (LISTENER_PATH.equals(path)) {
                    break;
                }
                String appName = path.substring(LISTENER_PATH.length() + 1);
                if (appName.indexOf("/") > 0) {
                    log.error("illegal application name: {}", appName);
                    break;
                }
                mapping.remove(appName);
            }
            break;
            case NODE_CHANGED: {
                log.info("set_data, oldData: {}, newData: {}", oldData, data);
                if (data == null) {
                    break;
                }
                String path = data.getPath();
                if (LISTENER_PATH.equals(path)) {
                    break;
                }
                String appName = path.substring(LISTENER_PATH.length() + 1);
                if (appName.indexOf("/") > 0) {
                    log.error("illegal resource name: {}", appName);
                    break;
                }
                String runtimeInstance = new String(data.getData());
                log.info("path: {}, application name: {}, data: {}", path, appName, runtimeInstance);
                mapping.put(appName, runtimeInstance);
            }
            break;
            default: {
                log.info("node default");
            }
            break;
        }
    }

    public String get(String appName) {
        return mapping.get(appName);
    }
}
