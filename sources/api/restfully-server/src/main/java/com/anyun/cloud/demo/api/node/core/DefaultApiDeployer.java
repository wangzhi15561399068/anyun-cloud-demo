package com.anyun.cloud.demo.api.node.core;

import com.anyun.cloud.demo.api.node.core.common.ApiDeployer;
import com.anyun.cloud.demo.common.etcd.client.HttpRestfullyApiClient;
import com.anyun.common.lang.http.ApiServer;
import com.anyun.common.lang.zookeeper.ZookeeperClient;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 08/06/2017
 */
public class DefaultApiDeployer implements ApiDeployer {
    private static final String PATH_API = "/anyuncloud/api";
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultApiFinder.class);
    private ZookeeperClient zk;
    private HttpRestfullyApiClient http;
    private ApiServer apiServer;

    @Inject
    public DefaultApiDeployer(ZookeeperClient zk, HttpRestfullyApiClient http, ApiServer apiServer) {
        this.zk = zk;
        this.http = http;
        this.apiServer = apiServer;
    }

    @Override
    public void deploy() throws Exception {
    }
}
