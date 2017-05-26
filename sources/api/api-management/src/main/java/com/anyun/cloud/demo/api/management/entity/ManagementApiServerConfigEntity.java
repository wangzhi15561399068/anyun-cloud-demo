package com.anyun.cloud.demo.api.management.entity;

import com.anyun.cloud.demo.common.etcd.response.EtcdActionResponse;
import com.anyun.cloud.demo.common.etcd.EtcdErrorResponseException;
import com.anyun.cloud.demo.common.etcd.spi.entity.AbstractEtcdEntity;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 25/05/2017
 */
public class ManagementApiServerConfigEntity extends AbstractEtcdEntity<ManagementApiServerConfigEntity> {
    public static String ETCD_KEY_CONFIG_ZK = "/keys/config/api-portal";
    public static String ETCD_KEY_MGR_HOST = "host";
    public static String ETCD_KEY_MGR_PORT = "port";
    public static String ETCD_KEY_MGR_IDLE_TIMEOUT = "idle-timeout";
    public static String ETCD_KEY_MGR_SERVLET_MAPPING = "servlet-mapping-path";
    public static String ETCD_KEY_MGR_HTTP_SERVER_THREAD_JOIN = "thread-join";

    private String host = "0.0.0.0";
    private int port = 80;
    private long idleTimeout = 30000;
    private String apiServletMappingPath = "/api/*";
    private boolean joinServerThread = true;

    public ManagementApiServerConfigEntity buildFromEtcdActionResponse(EtcdActionResponse response)
            throws EtcdErrorResponseException {
        ManagementApiServerConfigEntity config = new ManagementApiServerConfigEntity();
        try {
            config.host = getStringValue(response, ETCD_KEY_MGR_HOST);
            config.port = getIntValue(response, ETCD_KEY_MGR_PORT);
            config.idleTimeout = getLongValue(response, ETCD_KEY_MGR_IDLE_TIMEOUT);
            config.apiServletMappingPath = getStringValue(response, ETCD_KEY_MGR_SERVLET_MAPPING);
            config.joinServerThread = getBooleanValue(response, ETCD_KEY_MGR_HTTP_SERVER_THREAD_JOIN);
            return config;
        } catch (Exception ex) {
            throw new EtcdErrorResponseException(ex);
        }
    }

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

    @Override
    public String toString() {
        return "ManagementApiServerConfigEntity{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", idleTimeout=" + idleTimeout +
                ", apiServletMappingPath='" + apiServletMappingPath + '\'' +
                ", joinServerThread=" + joinServerThread +
                '}';
    }
}
