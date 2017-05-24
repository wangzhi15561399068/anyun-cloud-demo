package com.anyun.cloud.demo.api.management.http;

import com.anyun.cloud.demo.api.management.core.GuiceServiceModule;
import com.google.inject.name.Named;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import java.util.concurrent.CountDownLatch;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 23/05/2017
 */
public class JettyManagementApiServer implements ManagementApiServer<Server> {
    private static final Logger LOGGER = LoggerFactory.getLogger(JettyManagementApiServer.class);
    private CountDownLatch countDownLatch;
    private Server server;
    private ServerConfig config;
    private ServletHandler apiHandler;
    private JettyServerThreadRunable runable;
    private Class apiProcessServlet;

    @Inject
    public JettyManagementApiServer(
            @Named(GuiceServiceModule.NAMED_CONFIG_HTTP) ServerConfig config,
            @Named(GuiceServiceModule.NAMED_MGR_SERVLET_HANDLER) ServletHandler apiHandler,
            @Named(GuiceServiceModule.NAMED_MGR_SERVLET) Class apiProcessServlet) {
        this.config = config;
        this.apiHandler = apiHandler;
        this.apiProcessServlet = apiProcessServlet;
    }

    @Override
    public void start() throws Exception {
        if (runable != null) {
            LOGGER.error("Management api server is running");
            throw new Exception("Management api server is running");
        }
        countDownLatch = new CountDownLatch(1);
        server = new Server();
        LOGGER.info("Release a management api server instance");
        ServerConnector http = new ServerConnector(server);
        http.setHost(this.config.getHost());
        LOGGER.info("Bind management api server host to [{}]", this.config.getHost());
        http.setPort(this.config.getPort());
        LOGGER.info("Bind management api server port to [{}]", this.config.getPort());
        http.setIdleTimeout(this.config.getIdleTimeout());
        server.addConnector(http);
        server.setHandler(apiHandler);
        apiHandler.addServletWithMapping(apiProcessServlet, config.getApiServletMappingPath());
        String bindPath = "http://" + config.getHost() + ":" + config.getPort()
                + config.getApiServletMappingPath();
        LOGGER.debug("Bind servlet root path [{}]", bindPath);
        runable = new JettyServerThreadRunable(this);
        runable.start();
        LOGGER.info("Waiting for management api server start");
        countDownLatch.await();
    }

    @Override
    public void stop() throws Exception {

    }

    @Override
    public void restart() throws Exception {

    }

    @Override
    public ServerStatus getStatus() throws Exception {
        return null;
    }

    @Override
    public Server getServerInstance() {
        return server;
    }

    /**
     * @auth TwitchGG <twitchgg@yahoo.com>
     * @since 1.0.0 on 23/05/2017
     */
    public static class JettyServerThreadRunable implements Runnable {
        private static final Logger LOGGER = LoggerFactory.getLogger(JettyServerThreadRunable.class);
        private JettyManagementApiServer server;
        private Thread thread;

        @Inject
        public JettyServerThreadRunable(JettyManagementApiServer server) {
            this.thread = new Thread(this);
            this.server = server;
        }

        /**
         *
         */
        @Override
        public void run() {
            try {
                server.server.start();
                server.countDownLatch.countDown();
                LOGGER.info("Http api server start success");
                if (server.config.isJoinServerThread())
                    server.server.join();
            } catch (Exception e) {
                LOGGER.error("Http api server start error [{}]", e.getMessage(), e);
                System.exit(1);
            }
        }

        public void start() {
            thread.start();
        }
    }
}
