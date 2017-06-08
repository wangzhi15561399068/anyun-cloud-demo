package com.anyun.cloud.demo.api.node.core;

import com.anyun.cloud.demo.api.node.core.common.ApiFinder;
import com.anyun.cloud.demo.api.node.core.common.NodeApiComponent;
import com.anyun.cloud.demo.common.etcd.spi.entity.api.ApiResourceEntity;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 08/06/2017
 */
public class DefaultNodeApiComponent implements NodeApiComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultNodeApiComponent.class);
    private static final int DEPLOY_COUNT_MIN = 3;
    private ApiFinder apiFinder;

    @Inject
    public DefaultNodeApiComponent(ApiFinder apiFinder) {
        this.apiFinder = apiFinder;
    }

    @Override
    public ApiResourceEntity findResource(String id, String method) throws Exception {
        ApiResourceEntity apiResourceEntity = apiFinder.findApiResourceDeployInfo(id);
        if (apiResourceEntity == null)
            return null;
        if (!method.toLowerCase().equals(apiResourceEntity.getMethod().toLowerCase()))
            return null;
        return apiResourceEntity;
    }

    @Override
    public boolean mustDeploy(String id) throws Exception {
        int deployCount = apiFinder.findApiDeployNodesById(id).size();
        LOGGER.debug("Resource [{}] deploy node size [{}]", id, deployCount);
        if (deployCount < DEPLOY_COUNT_MIN)
            return true;
        return false;
    }
}
