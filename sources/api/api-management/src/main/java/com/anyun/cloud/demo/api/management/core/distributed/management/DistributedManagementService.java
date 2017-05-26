package com.anyun.cloud.demo.api.management.core.distributed.management;

import com.anyun.cloud.demo.api.management.entity.ManagementApiServerConfigEntity;
import com.anyun.cloud.demo.common.etcd.EtcdErrorResponseException;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 22/05/2017
 */
public interface DistributedManagementService {

    /**
     * @return
     * @throws EtcdErrorResponseException
     */
    ManagementApiServerConfigEntity getManagementApiServerConfig() throws EtcdErrorResponseException;

    /**
     * @throws Exception
     */
    void regist() throws Exception;
}
