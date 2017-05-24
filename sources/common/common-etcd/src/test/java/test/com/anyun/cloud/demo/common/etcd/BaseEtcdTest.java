package test.com.anyun.cloud.demo.common.etcd;

import com.anyun.cloud.demo.common.etcd.client.ClientConfig;
import com.anyun.cloud.demo.common.etcd.client.HttpRestfullyApiClient;
import com.anyun.cloud.demo.common.etcd.client.HttpRestfullyApiClientBuilder;
import com.anyun.common.lang.NetworkUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 2017/5/19
 */
public class BaseEtcdTest extends Assert {
    private static final String DOMAIN_ETCD = "etcd.dev.hohhot.ga.gov";
    private static final String BASE_URL = "http://" + DOMAIN_ETCD + ":2379/v2";
    private HttpRestfullyApiClientBuilder clientBuilder;
    private HttpRestfullyApiClient client;

    @Before
    public void before() throws Exception {
        ClientConfig config = new ClientConfig();
        config.setEtcdBaseUrl(BASE_URL);
        clientBuilder = new HttpRestfullyApiClientBuilder(config);
        client = clientBuilder.build();
    }

    @Test
    public void test1() throws Exception {
        assertTrue(NetworkUtils.isAddressAvailable(DOMAIN_ETCD));
        String method = "keys";
        String result = client.get(method);
        assertNotNull(result);
        System.out.println(result);
    }


}
