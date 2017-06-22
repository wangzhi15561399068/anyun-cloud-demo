package test.com.anyun.cloud.management.web;

import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.PathResource;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.lang.management.ManagementFactory;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 21/06/2017
 */
public class WebServer1 {
    public static void main(String[] args) throws Exception {
        String webApppPath = "/Users/twitchgg/Downloads/gtvg-ci.war";
        String tmp = "/Users/twitchgg/Develop/temp/hohot-cloud-demo/web";
        Resource warResource = new PathResource(new File(webApppPath));
        Server server = new Server(8080);
        MBeanContainer mbContainer = new MBeanContainer(
                ManagementFactory.getPlatformMBeanServer());
        server.addBean(mbContainer);
        WebAppContext webapp = new WebAppContext();
        webapp.setWarResource(warResource);
        webapp.setTempDirectory(new File(tmp));
        webapp.setContextPath("/");
        server.setHandler(webapp);
        server.start();
        server.join();
    }
}
