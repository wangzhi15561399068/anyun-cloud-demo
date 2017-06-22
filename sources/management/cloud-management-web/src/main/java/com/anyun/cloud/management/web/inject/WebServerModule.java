package com.anyun.cloud.management.web.inject;

import com.anyun.cloud.management.web.config.*;
import com.anyun.cloud.management.web.server.DefaultWebServer;
import com.anyun.cloud.management.web.server.WebServer;
import com.anyun.common.lang.options.ApplicationOptions;
import com.google.inject.AbstractModule;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 21/06/2017
 */
public class WebServerModule extends AbstractModule {
    private ApplicationOptions options;

    public WebServerModule(String[] args) {
        this.options = new WebServerOptions(args);
    }

    @Override
    protected void configure() {
        bind(ApplicationOptions.class).toInstance(options);
        bind(WebAppConfigBuilder.class).to(DefaultWebAppConfigBuilder.class);
        bind(HandlerListBuilder.class).to(DefaultHandlerListBuilder.class);
        bind(WebServer.class).to(DefaultWebServer.class);
    }
}
