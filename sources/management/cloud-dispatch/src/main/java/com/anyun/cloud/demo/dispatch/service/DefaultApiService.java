package com.anyun.cloud.demo.dispatch.service;

import com.anyun.cloud.demo.dispatch.service.entity.CloudResourceEntity;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 17/06/2017
 */
public class DefaultApiService implements ApiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultApiService.class);
    private static final String PATH_ETCD_API_DEPLOY = "/keys/api/deploy";
    private Etcd etcd;
    private Zk zk;

    @Inject
    public DefaultApiService(Zk zk, Etcd etcd) {
        this.zk = zk;
        this.etcd = etcd;
    }

    @Override
    public List<String> getUnDeployedApis() throws Exception {
        List<CloudResourceEntity> cloudResourceEntities = etcd.getDeployedApiResources();
        List<String> deployedApiNames = zk.getDeployedApiNames();
        LOGGER.debug("Must deploy api size: {},is deployed api size: {}",
                cloudResourceEntities.size(), deployedApiNames.size());
        return null;
    }


}
