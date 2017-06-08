package test.com.anyun.cloud.demo.api.node;

import com.anyun.common.lang.HashIdGenerator;
import org.junit.Assert;
import org.junit.Test;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 09/06/2017
 */
public class IdTest extends Assert {

    @Test
    public void test() throws Exception {
        String id = "/app1/api/v1_0_0/api1";
        System.out.println(HashIdGenerator.generate(id));
        System.out.println(HashIdGenerator.generate("/app1/api/v1_0_0/api1/api1_1"));
    }
}
