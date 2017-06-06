package com.anyun.common.service;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 06/06/2017
 */
public interface CloudServiceStub {

    /**
     * 获取服务名称
     *
     * @return name
     */
    String getName();

    /**
     * 是否是异步服务
     *
     * @return
     */
    boolean isAsync();
}
