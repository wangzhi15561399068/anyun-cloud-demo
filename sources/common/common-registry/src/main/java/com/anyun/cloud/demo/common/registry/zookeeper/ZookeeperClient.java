package com.anyun.cloud.demo.common.registry.zookeeper;

import org.apache.curator.framework.CuratorFramework;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 2017/5/18
 */
public class ZookeeperClient {
    private static ZookeeperClient client;
    CuratorFramework curatorFramework;
    private ZookeeperClient() {
    }

    public static ZookeeperClient getClient() {
        synchronized (ZookeeperClient.class) {
            if(client == null)
                client = new ZookeeperClient();
        }
        return client;
    }
}
