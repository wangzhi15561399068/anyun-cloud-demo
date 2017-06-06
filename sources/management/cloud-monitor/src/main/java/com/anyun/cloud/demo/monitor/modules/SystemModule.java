package com.anyun.cloud.demo.monitor.modules;

import com.anyun.cloud.demo.common.etcd.client.ClientConfig;
import com.anyun.cloud.demo.common.etcd.client.HttpRestfullyApiClient;
import com.anyun.cloud.demo.common.etcd.client.OkHttpRestfullyApiClient;
import com.anyun.cloud.demo.common.etcd.client.TtlUpdater;
import com.anyun.cloud.demo.common.etcd.spi.EtcdExtenedService;
import com.anyun.cloud.demo.common.etcd.spi.impl.EtcdExtenedServiceImpl;
import com.anyun.common.lang.thread.ScheduledExecutorServiceRunner;
import com.google.inject.name.Names;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 2017/6/6
 */
public class SystemModule extends AbstractCloudModules {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemModule.class);

    @Override
    protected void init() throws Exception {

    }
}
