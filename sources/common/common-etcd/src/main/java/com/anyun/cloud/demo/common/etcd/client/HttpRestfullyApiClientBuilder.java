package com.anyun.cloud.demo.common.etcd.client;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 2017/5/18
 */
public class HttpRestfullyApiClientBuilder {
    private ClientConfig clientConfig;

    public HttpRestfullyApiClientBuilder(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    private HttpRestfullyApiClientBuilder() {
    }

    public HttpRestfullyApiClient build() throws Exception{
        HttpRestfullyApiClient client = new HttpRestfullyApiClient(clientConfig);
        return client;
    }
}
