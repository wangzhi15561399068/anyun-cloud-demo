package test.com.anyun.cloud.demo.api.node;

import com.anyun.cloud.demo.api.node.http.HttpServer;
import com.anyun.cloud.demo.api.node.http.HttpServerConfig;
import org.junit.Before;
import org.junit.Test;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 22/05/2017
 */
public class HttpServerTest extends BaseApiNodeTest {
    private HttpServerConfig config;
    private String[] args;

    @Before
    public void before() {
        args = new String[]{};
        config = new HttpServerConfig();
        config.setPort(8080);
    }

    @Test
    public void test1() throws Exception {
        HttpServer server = HttpServer.getHttpServer();
        server.start(config);
    }
}
