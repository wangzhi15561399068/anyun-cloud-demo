package com.anyun.cloud.demo.api.management.http;

import com.anyun.cloud.demo.api.management.core.distributed.management.DistributedManagementService;
import com.anyun.cloud.demo.api.management.core.module.HttpApiServerBindingModule;
import com.anyun.cloud.demo.api.management.entity.ManagementApiServerConfigEntity;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 23/05/2017
 */
@Singleton
public class JettyManagementApiServer implements ManagementApiServer<Server> {
    private static final Logger LOGGER = LoggerFactory.getLogger(JettyManagementApiServer.class);
    private CountDownLatch countDownLatch;
    private Server server;
    private DistributedManagementService distributedManagementService;
    private ManagementApiServerConfigEntity config;
    private ServletHandler apiHandler;
    private JettyServerThreadRunable runable;
    private Class apiProcessServlet;
    private ServerStatus status;

    @Inject
    public JettyManagementApiServer(
            DistributedManagementService distributedManagementService,
            @Named(HttpApiServerBindingModule.NAMED_MGR_SERVLET_HANDLER) ServletHandler apiHandler,
            @Named(HttpApiServerBindingModule.NAMED_MGR_SERVLET) Class apiProcessServlet) {
        this.distributedManagementService = distributedManagementService;
        this.apiHandler = apiHandler;
        this.apiProcessServlet = apiProcessServlet;
        status = new ServerStatus();
    }

    @Override
    public void start() throws Exception {
        if (runable != null) {
            LOGGER.error("Management api server is running");
            throw new Exception("Management api server is running");
        }
        countDownLatch = new CountDownLatch(1);
        config = distributedManagementService.getManagementApiServerConfig();
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
        LOGGER.info("Restarting management api server instance..............");
        server.stop();
        apiHandler.setServletMappings(null);
        runable = null;
    }

    @Override
    public void restart() throws Exception {
        stop();
        start();
    }

    @Override
    public ServerStatus getStatus() throws Exception {
        return status;
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
                long readyTime = System.currentTimeMillis();
                server.status.setRunning(false);
                server.server.start();
                server.countDownLatch.countDown();
                LOGGER.info("Http api server start success");

                server.status.setRunning(true);
                long currentTime = System.currentTimeMillis();
                server.status.setStartupTime(new Date(currentTime));
                server.status.setStartMillisecond(currentTime - readyTime);
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
