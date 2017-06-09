package com.anyun.cloud.demo.api.node.nats;

import com.anyun.cloud.demo.common.etcd.GsonUtil;
import com.anyun.cloud.demo.common.etcd.client.HttpRestfullyApiClient;
import com.anyun.cloud.demo.common.etcd.response.EtcdActionNode;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.nats.client.Connection;
import io.nats.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 09/06/2017
 */
@Singleton
public class DefaultNatsClient implements NatsClient, Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultNatsClient.class);
    private static final String PATH_NATS_CONF = "/keys/config/nats";
    private HttpRestfullyApiClient etcd;
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Thread natsThread;

    @Inject
    public DefaultNatsClient(HttpRestfullyApiClient etcd) {
        this.etcd = etcd;
    }

    @Override
    public void start() throws Exception {
        natsThread = new Thread(this);
        natsThread.start();
    }

    @Override
    public void stop() throws Exception {
        connection.close();
        connection = null;
        connectionFactory = null;
        natsThread = null;
    }

    @Override
    public void run() {
        try {
            String configJson = etcd.get(PATH_NATS_CONF, null);
            LOGGER.debug("Nats connection config: {}", configJson);
            EtcdActionNode response = GsonUtil.getUtil().getReponseEntity(configJson).getActionNode();
            String connectionString = response.getNodeValueByName("connection-string");
            LOGGER.debug("Nats connection string: {}", connectionString);
            connectionFactory = new ConnectionFactory(connectionString);
            connection = connectionFactory.createConnection();
            LOGGER.info("Nats server connection created");
        } catch (Exception ex) {
            LOGGER.error("Nats client start error: {}", ex.getMessage(), ex);
            System.exit(1);
        }
    }
}
