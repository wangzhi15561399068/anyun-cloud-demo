package test.com.anyun.common.service;

import com.anyun.common.lang.FileUtil;
import org.xeustechnologies.jcl.JarClassLoader;

import java.net.URL;
import java.util.List;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 30/06/2017
 */
public class TestClassLoader {
    public static void main(String[] args) throws Exception {
        String jarFilePath = "/Users/twitchgg/Develop/Projects/hohot-cloud-demo/sources/example-service/example1/target";
        List<URL> jarFileUrls = FileUtil.resolveJarsByDirectory(jarFilePath);
        JarClassLoader jcl = new JarClassLoader();
        for (URL url : jarFileUrls) {
            System.out.println(url);
            jcl.add(url);
        }
    }
}
