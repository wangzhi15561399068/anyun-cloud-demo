package com.anyun.cloud.demo.api.management.core.distributed.management;

import com.anyun.cloud.demo.api.management.entity.ManagementApiServerConfigEntity;
import com.anyun.cloud.demo.common.etcd.client.HttpRestfullyApiClient;
import com.anyun.cloud.demo.common.etcd.client.OkHttpRestfullyApiClient;
import com.anyun.cloud.demo.common.etcd.response.EtcdActionResponse;
import com.anyun.cloud.demo.common.etcd.response.EtcdErrorResponseException;
import com.anyun.cloud.demo.common.etcd.util.GsonUtil;
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

    @Inject
    public DistributedManagementServiceImpl(HttpRestfullyApiClient client) {
        this.client = client;
        System.out.println(((OkHttpRestfullyApiClient)client).getConfig());
    }

    @Override
    public ManagementApiServerConfigEntity getManagementApiServerConfig() throws EtcdErrorResponseException {
        String configJsonStr = null;
        LOGGER.debug("Query management api server config from distributed etc server");
        try {
            configJsonStr = client.get(ManagementApiServerConfigEntity.ETCD_KEY_CONFIG_ZK, null);
            LOGGER.debug("Management api server config entity JSON [\n{}\n]", configJsonStr);
        } catch (IOException e) {
            LOGGER.error("Response error: {}", e.getMessage(), e);
            throw new EtcdErrorResponseException(e.getMessage(), e);
        }
        EtcdActionResponse response = GsonUtil.getUtil().getReponseEntity(configJsonStr);
        ManagementApiServerConfigEntity config = new ManagementApiServerConfigEntity().buildFromEtcdActionResponse(response);
        LOGGER.debug("Queried mgr api server host: " + config.getHost());
        LOGGER.debug("Queried mgr api server port: " + config.getPort());
        LOGGER.debug("Queried mgr api server idle timeout: " + config.getIdleTimeout());
        LOGGER.debug("Queried mgr api server api servlet mapping: " + config.getApiServletMappingPath());
        LOGGER.debug("Queried mgr api server is join server thread: " + config.isJoinServerThread());
        return config;
    }
}
