package com.anyun.common.service.common;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 12/06/2017
 */
public interface ServiceDeployer {
    void deploy(Class<? extends Service> aClass) throws Exception;
}
