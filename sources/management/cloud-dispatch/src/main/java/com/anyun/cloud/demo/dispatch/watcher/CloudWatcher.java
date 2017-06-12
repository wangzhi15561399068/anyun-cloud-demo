package com.anyun.cloud.demo.dispatch.watcher;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 09/06/2017
 */
public abstract class CloudWatcher implements CuratorWatcher {
    private String watchPath = "";
    private CuratorFramework curatorFramework;

    public CloudWatcher(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }

    public CloudWatcher withPath(String path) {
        this.watchPath = path;
        return this;
    }

    public void start() throws Exception {
        watch();
    }

    @Override
    public void process(WatchedEvent watchedEvent) throws Exception {
        watch();
    }

    private void watch() throws Exception {
        curatorFramework.getChildren().usingWatcher(this).forPath(watchPath);
    }

    public abstract void watcherProcess(WatchedEvent watchedEvent) throws Exception;
}
