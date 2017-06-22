package com.anyun.cloud.management.web;

import com.anyun.cloud.management.web.inject.ThymesModule;
import com.anyun.cloud.management.web.inject.WebServerModule;
import com.anyun.cloud.management.web.server.WebServer;
import com.anyun.common.lang.bean.InjectorsBuilder;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 21/06/2017
 */
public class WebServerBoot {
    public static void main(String[] args) throws Exception {
        InjectorsBuilder.getBuilder().build(
                new WebServerModule(args),
                new ThymesModule()
        );
        InjectorsBuilder.getBuilder().getInstanceByType(WebServer.class).start();
    }
}
