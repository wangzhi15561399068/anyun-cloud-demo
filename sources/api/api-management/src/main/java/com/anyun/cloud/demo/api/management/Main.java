package com.anyun.cloud.demo.api.management;

import com.anyun.cloud.demo.api.management.core.GuiceServiceModule;
import com.anyun.cloud.demo.api.management.http.ManagementApiServer;
import com.anyun.common.lang.bean.InjectorsBuilder;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 22/05/2017
 */
public class Main {
    public static void main(String[] args) throws Exception {
        InjectorsBuilder.getBuilder().build(new GuiceServiceModule());
        ManagementApiServer server = InjectorsBuilder.getBuilder().getInstanceByType(ManagementApiServer.class);
        server.start();
    }
}
