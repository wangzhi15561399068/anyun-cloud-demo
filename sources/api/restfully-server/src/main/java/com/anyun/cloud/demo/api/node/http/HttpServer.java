package com.anyun.cloud.demo.api.node.http;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 2017/5/19
 */
public class HttpServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
    private static HttpServer httpServer;
    private CountDownLatch countDownLatch;
    private Server server;
    private HttpServerConfig config;
    private ServletHandler apiHandler;
    private HttpServerThreadRunable runable;

    private HttpServer() {

    }

    /**
     * @return
     */
    public static HttpServer getHttpServer() {
        synchronized (HttpServer.class) {
            if (httpServer == null)
                httpServer = new HttpServer();
        }
        return httpServer;
    }

    /**
     * @param config
     * @throws Exception
     */
    public void start(HttpServerConfig config) throws Exception {
        if (runable != null) {
            LOGGER.error("Http api server is running");
            throw new Exception("Http api server is running");
        }
        countDownLatch = new CountDownLatch(1);
        this.config = config;
        this.apiHandler = config.getServletHandler();
        server = new Server();
        LOGGER.info("Release a http api server instance");
        ServerConnector http = new ServerConnector(server);
        http.setHost(this.config.getHost());
        LOGGER.info("Bind http api server host to [{}]", this.config.getHost());
        http.setPort(this.config.getPort());
        LOGGER.info("Bind http api server port to [{}]", this.config.getPort());
        http.setIdleTimeout(this.config.getIdleTimeout());
        server.addConnector(http);
        server.setHandler(apiHandler);
        LOGGER.info("Bind http api server tcp handler to [{}]", apiHandler.getClass().getCanonicalName());
        if (config.getApiProcessServlet() != null) {
            apiHandler.addServletWithMapping(config.getApiProcessServlet().getClass(),
                    config.getApiServletMappingPath());
            String bindPath = "http://" + config.getHost() + ":" + config.getPort()
                    + config.getApiServletMappingPath();
            LOGGER.info("Bind http api server sevlet to [{}]",
                    config.getApiProcessServlet().getClass().getCanonicalName());
            LOGGER.info("Bind http api server root path to [{}]", bindPath);
        }
        runable = new HttpServerThreadRunable(server, config);
        runable.start();
        LOGGER.info("Waiting for http api server start");
        countDownLatch.await();
    }

    public void stop() throws Exception {
        this.server.stop();
        this.runable = null;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    /**
     * @auth TwitchGG <twitchgg@yahoo.com>
     * @since 1.0.0 on 2017/5/19
     */
    private static class HttpServerThreadRunable implements Runnable {
        private static final Logger LOGGER = LoggerFactory.getLogger(HttpServerThreadRunable.class);
        private Server server;
        private HttpServerConfig config;
        private Thread thread;

        public HttpServerThreadRunable(Server server, HttpServerConfig config) {
            this.config = config;
            this.server = server;
            this.thread = new Thread(this);
        }

        /**
         *
         */
        @Override
        public void run() {
            try {
                server.start();
                httpServer.getCountDownLatch().countDown();
                LOGGER.info("Http api server start success");
                if (config.isJoinServerThread())
                    server.join();
            } catch (Exception e) {
                LOGGER.error("Http api server start error [{}]", e.getMessage(), e);
                System.exit(1);
            }
        }

        public void start() {
            thread.start();
        }

        public Server getServer() {
            return server;
        }

        public void setServer(Server server) {
            this.server = server;
        }

        public HttpServerConfig getConfig() {
            return config;
        }

        public void setConfig(HttpServerConfig config) {
            this.config = config;
        }

        public Thread getThread() {
            return thread;
        }

        public void setThread(Thread thread) {
            this.thread = thread;
        }
    }
}
