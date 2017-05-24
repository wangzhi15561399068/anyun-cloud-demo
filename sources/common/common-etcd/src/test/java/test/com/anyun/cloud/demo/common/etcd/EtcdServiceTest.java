package test.com.anyun.cloud.demo.common.etcd;

import com.anyun.cloud.demo.common.etcd.client.ClientConfig;
import com.anyun.cloud.demo.common.etcd.client.HttpRestfullyApiClient;
import com.anyun.cloud.demo.common.etcd.client.OkHttpRestfullyApiClient;
import com.anyun.cloud.demo.common.etcd.response.EtcdErrorResponseException;
import com.anyun.cloud.demo.common.etcd.spi.EtcdExtenedSpi;
import com.anyun.cloud.demo.common.etcd.spi.entity.ZookeeperConfigEntity;
import com.anyun.cloud.demo.common.etcd.spi.impl.EtcdExtenedService;
import org.junit.Before;
import org.junit.Test;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 25/05/2017
 */
public class EtcdServiceTest extends BaseEtcdTest {
    private ClientConfig config;
    private HttpRestfullyApiClient client;
    private EtcdExtenedSpi spi;

    @Before
    public void before() throws Exception {
        config = new ClientConfig();
        config.setHost("192.168.103.7");
        client = new OkHttpRestfullyApiClient(config);
        spi = new EtcdExtenedService(client);
    }

    @Test
    public void testQueryZookeeperConfigService() throws EtcdErrorResponseException {
        ZookeeperConfigEntity config = spi.getZookeeperConfigResponse();
        assertNotNull(config);
    }
}
