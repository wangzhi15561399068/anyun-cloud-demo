package com.anyun.cloud.demo.api.management.core.distributed.management;

import com.anyun.cloud.demo.api.management.entity.ManagementApiServerConfigEntity;
import com.anyun.cloud.demo.common.etcd.EtcdErrorResponseException;
import com.anyun.cloud.demo.common.etcd.GsonUtil;
import com.anyun.cloud.demo.common.etcd.client.HttpRestfullyApiClient;
import com.anyun.cloud.demo.common.etcd.client.OkHttpRestfullyApiClient;
import com.anyun.cloud.demo.common.etcd.response.EtcdActionResponse;
import com.anyun.cloud.demo.common.registry.entity.NodeType;
import com.anyun.cloud.demo.common.registry.service.NodeRegistService;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 25/05/2017
 */
public class DistributedManagementServiceImpl implements DistributedManagementService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DistributedManagementService.class);
    private HttpRestfullyApiClient client;
    private NodeRegistService nodeRegistService;

    @Inject
    public DistributedManagementServiceImpl(HttpRestfullyApiClient client, NodeRegistService nodeRegistService) {
        this.client = client;
        this.nodeRegistService = nodeRegistService;
        System.out.println(((OkHttpRestfullyApiClient) client).getConfig());
    }

    @Override
    public ManagementApiServerConfigEntity getManagementApiServerConfig() throws EtcdErrorResponseException {
        String configJsonStr = null;
        LOGGER.debug("Discovery management api server config from distributed etc server");
        try {
            configJsonStr = client.get(ManagementApiServerConfigEntity.ETCD_KEY_CONFIG_ZK, null);
            LOGGER.debug("Management api server config entity JSON [\n{}\n]", configJsonStr);
        } catch (IOException e) {
            LOGGER.error("Response error: {}", e.getMessage(), e);
            throw new EtcdErrorResponseException(e.getMessage(), e);
        }
        EtcdActionResponse response = GsonUtil.getUtil().getReponseEntity(configJsonStr);
        ManagementApiServerConfigEntity config = new ManagementApiServerConfigEntity().buildFromEtcdActionResponse(response);
        LOGGER.debug("Discovered management api server host: " + config.getHost());
        LOGGER.debug("Discovered management api server port: " + config.getPort());
        LOGGER.debug("Discovered management api server idle timeout: " + config.getIdleTimeout());
        LOGGER.debug("Discovered management api server api servlet mapping: " + config.getApiServletMappingPath());
        LOGGER.debug("Discovered management api server is join server thread: " + config.isJoinServerThread());
        return config;
    }

    @Override
    public void regist() throws Exception {
        nodeRegistService.registNode(NodeType.MGR_NODE);
    }
}
