package com.anyun.cloud.demo.common.registry.client;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 07/06/2017
 */
public interface RegisterClient {

    /**
     *
     * @return device id
     * @throws Exception
     */
    String regist() throws Exception;

    /**
     *
     * @throws Exception
     */
    void loopThread() throws Exception;
}
