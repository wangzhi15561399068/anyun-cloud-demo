package com.anyun.cloud.demo.common.etcd.spi;

import com.anyun.cloud.demo.common.etcd.EtcdErrorResponseException;
import com.anyun.cloud.demo.common.etcd.spi.entity.ZookeeperConfigEntity;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 2017/5/17
 */
public interface EtcdExtenedService {
    /**
     * @throws Exception
     */
    void checkIngEtcdServer() throws Exception;

    /**
     * @return
     * @throws EtcdErrorResponseException
     */
    ZookeeperConfigEntity getZookeeperConfigResponse() throws EtcdErrorResponseException;
}
