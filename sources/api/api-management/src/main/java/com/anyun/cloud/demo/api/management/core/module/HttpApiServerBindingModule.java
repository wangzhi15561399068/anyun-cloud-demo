package com.anyun.cloud.demo.api.management.core.module;

import com.anyun.cloud.demo.api.management.http.DefaultManagementApiServlet;
import com.anyun.cloud.demo.api.management.http.JettyManagementApiServer;
import com.anyun.cloud.demo.api.management.http.ManagementApiServer;
import com.anyun.cloud.demo.api.management.http.ServerConfig;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.eclipse.jetty.servlet.ServletHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 25/05/2017
 */
public class HttpApiServerBindingModule extends AbstractModule {
    public static final String NAMED_CONFIG_HTTP = "config.http.api";
    public static final String NAMED_MGR_SERVLET_HANDLER = "config.http.api.servlet.handler";
    public static final String NAMED_MGR_SERVLET = "config.http.api.servlet";
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpApiServerBindingModule.class);

    @Override
    protected void configure() {
        ServerConfig config = new ServerConfig();
        config.setPort(8080);
        bind(ServerConfig.class).annotatedWith(Names.named(NAMED_CONFIG_HTTP)).toInstance(config);
        LOGGER.info("Bind management server config to: {}", config.toString());

        ServletHandler servletHandler = new ServletHandler();
        Class<?> servletClass = DefaultManagementApiServlet.class;
        bind(ServletHandler.class).annotatedWith(Names.named(NAMED_MGR_SERVLET_HANDLER)).toInstance(servletHandler);
        LOGGER.info("Bind management server servlet handler to: {}", ServletHandler.class.getCanonicalName());

        bind(Class.class).annotatedWith(Names.named(NAMED_MGR_SERVLET)).toInstance(servletClass);
        LOGGER.info("Bind management server base servlet to: {}", servletClass.getCanonicalName());

        bind(ManagementApiServer.class).to(JettyManagementApiServer.class);
        LOGGER.info("Bind management server implement to: {}", JettyManagementApiServer.class.getCanonicalName());
    }
}
