package com.anyun.common.service;

import com.anyun.cloud.demo.common.registry.client.RegisterClient;
import com.anyun.cloud.demo.common.registry.client.RegistryModule;
import com.anyun.cloud.demo.common.registry.client.RegistryOptions;
import com.anyun.cloud.demo.common.registry.entity.NodeType;
import com.anyun.common.lang.bean.InjectorsBuilder;
import com.anyun.common.lang.msg.NatsClient;
import com.anyun.common.service.annotation.CloudService;
import com.anyun.common.service.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 12/06/2017
 */
public class ServiceBoot {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceBoot.class);
    private static final String PREFIX_SERVICE_PKG = "service.";
    private static final String FILE_PROP = "services.properties";
    private PackageScanClassResolver resolver = new PackageScanClassResolver();
    private RegisterClient registerClient;

    private ServiceBoot() {
    }

    public static void boot(Class<?> clazz, String[] args) throws Exception {
        ServiceBoot boot = new ServiceBoot();
        boot.initGuiceService(args);
        boot.registerClient = InjectorsBuilder.getBuilder().getInstanceByType(RegisterClient.class);
//        List<String> packageNames = boot.getServicePackages(clazz);
        List<String> serviceNames = boot.getServices(clazz);
        List<Class<? extends Service>> allCloudServiceClasses = new ArrayList<>();
        for (String serviceName : serviceNames) {
//            allCloudServiceClasses.addAll(boot.resolver.withClassLoad(clazz.getClassLoader())
//                    .scanCloudServiceClassByPackageName(packageName));
            Class loadClass = Class.forName(serviceName);
            if (loadClass.getAnnotation(CloudService.class) != null) {
                Arrays.stream(loadClass.getInterfaces()).filter(c -> {
                    if (c.getName().equals(Service.class.getName())) {
                        LOGGER.debug("Resolve cloud service class: {}", loadClass);
                        allCloudServiceClasses.add((Class<? extends Service>) loadClass);
                        return true;
                    }
                    return false;
                }).findAny();
            }
        }
        String deviceId = boot.registerClient.regist(Arrays.asList(NodeType.SERVICE_NODE));
        InjectorsBuilder.getBuilder().getInstanceByType(ServiceCache.class).setDeviceId(deviceId);
        boot.registerClient.loopThread();
        InjectorsBuilder.getBuilder().getInstanceByType(NatsClient.class).start();
        for (Class<? extends Service> cloudServiceClass : allCloudServiceClasses) {
            InjectorsBuilder.getBuilder().getInstanceByType(ServiceDeployer.class).deploy(deviceId, cloudServiceClass);
        }
    }

    private void initGuiceService(String[] args) {
        RegistryOptions options = new RegistryOptions(args);
        InjectorsBuilder injectorsBuilder = InjectorsBuilder.getBuilder();
        injectorsBuilder.build(
                new RegistryModule(options),
                new ServiceCommonModule());
    }

    private List<String> getServices(Class<?> clazz) throws Exception {
        List<String> serviceNames = new ArrayList<>();
//        File propertiesFile = Resources.getResourceAsFile(clazz.getClassLoader(), FILE_PROP);
        Properties properties = new Properties();
        properties.load(Resources.getResourceAsStream(clazz.getClassLoader(), FILE_PROP));
        Enumeration<?> propKeys = properties.propertyNames();
        while (propKeys.hasMoreElements()) {
            String key = propKeys.nextElement().toString();
            if (!key.startsWith(PREFIX_SERVICE_PKG))
                continue;
            String serviceName = properties.getProperty(key);
            LOGGER.debug("Cloud service package name: {}", serviceName);
            serviceNames.add(serviceName);
        }
        return serviceNames;
    }
}
