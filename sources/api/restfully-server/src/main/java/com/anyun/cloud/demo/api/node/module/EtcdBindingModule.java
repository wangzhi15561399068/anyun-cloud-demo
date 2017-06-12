package com.anyun.cloud.demo.api.node.module;

import com.anyun.cloud.demo.common.etcd.client.ClientConfig;
import com.anyun.cloud.demo.common.etcd.client.HttpRestfullyApiClient;
import com.anyun.cloud.demo.common.etcd.client.OkHttpRestfullyApiClient;
import com.anyun.cloud.demo.common.etcd.spi.EtcdExtenedService;
import com.anyun.cloud.demo.common.etcd.spi.impl.EtcdExtenedServiceImpl;
import com.google.inject.AbstractModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 25/05/2017
 */
public class EtcdBindingModule extends AbstractModule {
    private static final Logger LOGGER = LoggerFactory.getLogger(EtcdBindingModule.class);

    @Override
    protected void configure() {
        ClientConfig etcdApiClientConfig = new ClientConfig();
        etcdApiClientConfig.setHost("etcd.dev.hohhot.ga.gov");
        bind(ClientConfig.class).toInstance(etcdApiClientConfig);
        LOGGER.info("Bind etcd api client config to: {}", etcdApiClientConfig.toString());

        bind(HttpRestfullyApiClient.class).to(OkHttpRestfullyApiClient.class);
        LOGGER.info("Bind etcd api client to: {}", OkHttpRestfullyApiClient.class.getCanonicalName());

        bind(EtcdExtenedService.class).to(EtcdExtenedServiceImpl.class);
        LOGGER.info("Bind etcd api extend service to: {}", EtcdExtenedServiceImpl.class.getCanonicalName());
    }
}
