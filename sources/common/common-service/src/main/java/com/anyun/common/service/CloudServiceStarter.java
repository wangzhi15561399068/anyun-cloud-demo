package com.anyun.common.service;

import com.anyun.common.service.annotation.CloudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 06/06/2017
 */
public class CloudServiceStarter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CloudServiceStarter.class);
    private static final String FILE_PROP = "META-INF/services.properties";
    private static final String SERVICE_PREFIX = "service.class.";
    private static final String NAMESPACE = "service.namespace";
    private static final String COLLECTION_NAME = "service.collection.name";
    private static CloudServiceStarter starter;
    private ClassLoader classLoader;

    private CloudServiceStarter() {

    }

    public static CloudServiceStarter getStarter() {
        synchronized (CloudServiceStarter.class) {
            if (starter == null)
                starter = new CloudServiceStarter();
        }
        return starter;
    }

    public void start(Class<?> startClass) throws Exception {
        this.classLoader = startClass.getClassLoader();
        Enumeration<URL> resources = getClass().getClassLoader()
                .getResources(FILE_PROP);
        Properties properties = null;
        if (resources.hasMoreElements())
            properties = new Properties();
        properties.load(resources.nextElement().openStream());
        Enumeration<?> propKeys = properties.propertyNames();
        String namespace = properties.getProperty(NAMESPACE);
        String collectionName = properties.getProperty(COLLECTION_NAME);
        LOGGER.debug("");
        while (propKeys.hasMoreElements()) {
            String key = propKeys.nextElement().toString();
            if (!key.startsWith(SERVICE_PREFIX))
                continue;
            Class<?> serviceClass = Class.forName(properties.getProperty(key));
            LOGGER.debug("Service class [{}]", serviceClass);
            checkService(serviceClass);
        }
    }

    private void checkService(Class<?> serviceClass) throws Exception {
        CloudService cloudService = serviceClass.getAnnotation(CloudService.class);
        if (cloudService == null)
            throw new Exception("[" + serviceClass.getCanonicalName() + "] is not a cloud service class");
        String busName = cloudService.busName();
        if (busName == null || busName.equals(""))
            busName = serviceClass.getCanonicalName();
        LOGGER.debug("Service [{}] bus name [{}]", serviceClass.getCanonicalName(), busName);

    }
}
