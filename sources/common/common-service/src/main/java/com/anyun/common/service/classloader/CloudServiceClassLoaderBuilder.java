package com.anyun.common.service.classloader;

import com.anyun.common.lang.StringUtils;
import com.anyun.common.service.common.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 16/06/2017
 */
public class CloudServiceClassLoaderBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(CloudServiceClassLoaderBuilder.class);
    private static final String SERVICE_PATH = "service";
    private String jarPath = "";
    private Class<?> aClass;

    public CloudServiceClassLoaderBuilder() {
    }

    public CloudServiceClassLoaderBuilder withDirectory(String path) {
        this.jarPath = path;
        return this;
    }


    public CloudServiceClassLoaderBuilder withBuildBaseClass(Class<?> aClass) {
        this.aClass = aClass;
        return this;
    }

    public CloudServiceClassLoader build() throws Exception {
        if (StringUtils.isEmpty(jarPath)) {
            if (aClass == null)
                jarPath = Resources.getRunDirectoyPath(
                        CloudServiceClassLoaderBuilder.class).getPath() + "/" + SERVICE_PATH;
            else
                jarPath = Resources.getRunDirectoyPath(aClass).getPath() + "/" + SERVICE_PATH;
        }
        LOGGER.debug("Service deploy directory: {}", jarPath);
        File[] files = new File(jarPath).listFiles((dir, name) -> {
            if (name.endsWith(".jar"))
                return true;
            return false;
        });
        URL[] urls = new URL[files.length];
        for (int i = 0; i < files.length; i++) {
            urls[i] = new File(files[i].getAbsolutePath()).toURI().toURL();
        }
        LOGGER.debug("Jar Files URL: {}", urls.length);
        CloudServiceClassLoader classLoader = new CloudServiceClassLoader(urls);
        return classLoader;
    }
}
