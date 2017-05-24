package com.anyun.cloud.demo.api.management.http;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 23/05/2017
 */
public interface ManagementApiServer<T> {

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

    /**
     *
     * @return
     * @throws Exception
     */
    ServerStatus getStatus() throws Exception;

    /**
     *
     * @return
     */
    T getServerInstance();
}
