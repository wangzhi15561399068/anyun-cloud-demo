package com.anyun.common.service.common;

import com.anyun.cloud.demo.common.etcd.client.DefaultNatsClient;
import com.anyun.common.lang.msg.NatsClient;
import com.google.inject.AbstractModule;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 12/06/2017
 */
public class ServiceCommonModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ServiceCache.class).toInstance(new ServiceCache());
        bind(NatsClient.class).to(DefaultNatsClient.class);
        bind(ServiceDeployer.class).to(DefaultServiceDeployer.class);
    }
}
