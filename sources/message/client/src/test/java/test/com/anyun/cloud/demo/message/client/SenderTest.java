package test.com.anyun.cloud.demo.message.client;

import io.nats.client.*;

import java.io.IOException;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 2017/6/5
 */
public class SenderTest {
    private static final String CONNSTR = "nats://192.168.103.6:4222";
    private static final String CHANNEL_TEST = "foo";

    public SenderTest() throws IOException {

    }

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory(CONNSTR);
        Connection nc = factory.createConnection();
        new Thread(() -> {
            try {
                while(true) {
                    byte[] m = new String("Hello-" + System.currentTimeMillis()).getBytes();
                    Message ms = nc.request(CHANNEL_TEST,m);
                    System.out.println("ms: " + ms);
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        Message message = nc.subscribe(CHANNEL_TEST).nextMessage();
        System.out.println(message);
        nc.publish(message.getReplyTo(),"test".getBytes());
    }
}
