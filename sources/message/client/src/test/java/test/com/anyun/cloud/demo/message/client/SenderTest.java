package test.com.anyun.cloud.demo.message.client;

import com.anyun.cloud.demo.message.client.statistics.StatisticsRunnable;
import io.nats.client.*;

import java.io.IOException;
import java.util.Random;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 2017/6/5
 */
public class SenderTest {
    private static final String CONNSTR = "msg://192.168.103.6:4222";
    private static final String CHANNEL_TEST = "foo";

    public SenderTest() throws IOException {

    }

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory(CONNSTR);
        Connection nc = factory.createConnection();
        new StatisticsRunnable(nc).withLoopTime(5).start();
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
        nc.subscribe(CHANNEL_TEST,message -> {
            System.out.println(message);
            try {
                Thread.sleep(new Random().nextInt(10* 1000));
                byte[] m = new String("ReplyTo-" + System.currentTimeMillis()).getBytes();
                nc.publish(message.getReplyTo(),m);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
