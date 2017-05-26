package com.anyun.cloud.demo.common.registry.zookeeper;

import org.apache.zookeeper.CreateMode;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 25/05/2017
 */
public interface ZookeeperClient {

    /**
     *
     * @param path
     * @return
     * @throws Exception
     */
    boolean exist(String path) throws Exception;

    /**
     *
     * @param path
     * @param createMode
     * @throws Exception
     */
    void createPath(String path,CreateMode createMode) throws Exception;

    /**
     *
     * @param path
     * @param data
     * @param createMode
     * @throws Exception
     */
    void createPath(String path,String data,CreateMode createMode) throws Exception;

    /**
     *
     * @throws Exception
     */
    void start() throws Exception;

    /**
     *
     * @throws Exception
     */
    void stop() throws Exception;

    /**
     *
     * @throws Exception
     */
    void restart() throws Exception;
}
