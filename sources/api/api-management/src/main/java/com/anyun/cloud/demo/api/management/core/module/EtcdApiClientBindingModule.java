package com.anyun.cloud.demo.api.management.core.module;

import com.anyun.cloud.demo.common.etcd.client.ClientConfig;
import com.anyun.cloud.demo.common.etcd.client.HttpRestfullyApiClient;
import com.anyun.cloud.demo.common.etcd.client.OkHttpRestfullyApiClient;
import com.anyun.cloud.demo.common.etcd.spi.EtcdExtenedSpi;
import com.anyun.cloud.demo.common.etcd.spi.impl.EtcdExtenedService;
import com.google.inject.AbstractModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 25/05/2017
 */
public class EtcdApiClientBindingModule extends AbstractModule {
    private static final Logger LOGGER = LoggerFactory.getLogger(EtcdApiClientBindingModule.class);

    @Override
    protected void configure() {
        ClientConfig etcdApiClientConfig = new ClientConfig();
        etcdApiClientConfig.setHost("192.168.103.7");
        bind(ClientConfig.class).toInstance(etcdApiClientConfig);
        LOGGER.info("Bind etcd api client config to: {}", etcdApiClientConfig.toString());

        bind(HttpRestfullyApiClient.class).to(OkHttpRestfullyApiClient.class);
        LOGGER.info("Bind etcd api client to: {}", OkHttpRestfullyApiClient.class.getCanonicalName());

        bind(EtcdExtenedSpi.class).to(EtcdExtenedService.class);
        LOGGER.info("Bind etcd api client to: {}", OkHttpRestfullyApiClient.class.getCanonicalName());
    }
}
