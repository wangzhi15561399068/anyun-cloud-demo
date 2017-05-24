package com.anyun.cloud.demo.api.node;

import com.anyun.cloud.demo.api.node.http.HttpServer;
import com.anyun.cloud.demo.api.node.http.HttpServerConfig;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 2017/5/16
 */
public class Main {
    public static void main(String[] args) throws Exception {
        HttpServerConfig config = new HttpServerConfig();
        HttpServer server = HttpServer.getHttpServer();
        server.start(config);
    }
}
