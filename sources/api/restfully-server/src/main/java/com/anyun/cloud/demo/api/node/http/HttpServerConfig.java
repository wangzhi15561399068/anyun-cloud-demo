package com.anyun.cloud.demo.api.node.http;

import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.http.HttpServlet;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 2017/5/19
 */
public class HttpServerConfig {
    private String host = "0.0.0.0";
    private int port = 8080;
    private long idleTimeout = 30000;
    private String apiServletMappingPath = "/api/*";
    private boolean joinServerThread = true;
    private ServletHandler servletHandler = new ServletHandler();
    private HttpServlet apiProcessServlet = new DefaultApiServlet();

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(long idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public String getApiServletMappingPath() {
        return apiServletMappingPath;
    }

    public void setApiServletMappingPath(String apiServletMappingPath) {
        this.apiServletMappingPath = apiServletMappingPath;
    }

    public boolean isJoinServerThread() {
        return joinServerThread;
    }

    public void setJoinServerThread(boolean joinServerThread) {
        this.joinServerThread = joinServerThread;
    }

    public ServletHandler getServletHandler() {
        return servletHandler;
    }

    public void setServletHandler(ServletHandler servletHandler) {
        this.servletHandler = servletHandler;
    }

    public HttpServlet getApiProcessServlet() {
        return apiProcessServlet;
    }

    public void setApiProcessServlet(HttpServlet apiProcessServlet) {
        this.apiProcessServlet = apiProcessServlet;
    }
}
