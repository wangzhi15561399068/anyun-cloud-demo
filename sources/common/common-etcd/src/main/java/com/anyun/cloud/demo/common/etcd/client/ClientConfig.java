package com.anyun.cloud.demo.common.etcd.client;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 2017/5/18
 */
public class ClientConfig {
    private String etcdBaseUrl;

    public String getEtcdBaseUrl() {
        return etcdBaseUrl;
    }

    public void setEtcdBaseUrl(String etcdBaseUrl) {
        this.etcdBaseUrl = etcdBaseUrl;
    }
}
