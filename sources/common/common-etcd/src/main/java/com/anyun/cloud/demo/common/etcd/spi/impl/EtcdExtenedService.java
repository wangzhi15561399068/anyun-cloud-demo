package com.anyun.cloud.demo.common.etcd.spi.impl;

import com.anyun.cloud.demo.common.etcd.client.HttpRestfullyApiClient;
import com.anyun.cloud.demo.common.etcd.response.EtcdActionResponse;
import com.anyun.cloud.demo.common.etcd.response.EtcdErrorResponseException;
import com.anyun.cloud.demo.common.etcd.spi.EtcdExtenedSpi;
import com.anyun.cloud.demo.common.etcd.spi.entity.ZookeeperConfigEntity;
import com.anyun.cloud.demo.common.etcd.util.GsonUtil;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 2017/5/18
 */
public class EtcdExtenedService implements EtcdExtenedSpi {
    private static final Logger LOGGER = LoggerFactory.getLogger(EtcdExtenedService.class);
    private HttpRestfullyApiClient client;

    @Inject
    public EtcdExtenedService(HttpRestfullyApiClient client) {
        this.client = client;
    }

    @Override
    public void checkIngEtcdServer() throws Exception {

    }

    @Override
    public ZookeeperConfigEntity getZookeeperConfigResponse() throws EtcdErrorResponseException {
        String zkConfigJsonStr = null;
        try {
            zkConfigJsonStr = client.get(EtcdApiUrls.KEY_CONFIG_ZK, null);
            LOGGER.debug("Zookeeper config entity JSON [\n{}\n]", zkConfigJsonStr);
        } catch (IOException e) {
            LOGGER.error("Response error: {}", e.getMessage(), e);
            throw new EtcdErrorResponseException(e.getMessage(), e);
        }
        EtcdActionResponse response = GsonUtil.getUtil().getReponseEntity(zkConfigJsonStr);
        ZookeeperConfigEntity config = new ZookeeperConfigEntity().buildFromEtcdActionResponse(response);
        LOGGER.debug("Queried zk connection string: " + config.getConnectingString());
        LOGGER.debug("Queried zk connection timeout: " + config.getConnectionTimeout());
        LOGGER.debug("Queried zk session timeout: " + config.getSessionTimeout());
        LOGGER.debug("Queried zk retry policy max retries: " + config.getRetryPolicyMaxRetries());
        LOGGER.debug("Queried zk retry policy retry policy sleep time: " + config.getRetryPolicySleepTime());
        return config;
    }
}
