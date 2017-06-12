package com.anyun.common.service;

import com.anyun.cloud.demo.common.registry.client.RegisterClient;
import com.anyun.cloud.demo.common.registry.client.RegistryModule;
import com.anyun.cloud.demo.common.registry.client.RegistryOptions;
import com.anyun.cloud.demo.common.registry.entity.NodeType;
import com.anyun.common.lang.bean.InjectorsBuilder;
import com.anyun.common.service.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 12/06/2017
 */
public class ServiceBoot {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceBoot.class);
    private static final String PREFIX_SERVICE_PKG = "service.package.";
    private static final String FILE_PROP = "META-INF/services.properties";
    private PackageScanClassResolver resolver = new PackageScanClassResolver();
    private RegisterClient registerClient;

    private ServiceBoot() {
    }

    public static void boot(String[] args) throws Exception {
        ServiceBoot boot = new ServiceBoot();
        boot.initGuiceService(args);
        boot.registerClient = InjectorsBuilder.getBuilder().getInstanceByType(RegisterClient.class);
        List<String> packageNames = boot.getServicePackages();
        List<Class<? extends Service>> allCloudServiceClasses = new ArrayList<>();
        for (String packageName : packageNames) {
            allCloudServiceClasses.addAll(boot.resolver.withBaseClass(ServiceBoot.class)
                    .scanCloudServiceClassByPackageName(packageName));
        }
        for (Class<? extends Service> cloudServiceClass : allCloudServiceClasses) {
            InjectorsBuilder.getBuilder().getInstanceByType(ServiceDeployer.class).deploy(cloudServiceClass);
        }
        boot.registerClient.regist(Arrays.asList(NodeType.SERVICE_NODE));
        boot.registerClient.loopThread();
    }

    private void initGuiceService(String[] args) {
        RegistryOptions options = new RegistryOptions(args);
        InjectorsBuilder injectorsBuilder = InjectorsBuilder.getBuilder();
        injectorsBuilder.build(
                new RegistryModule(options),
                new ServiceCommonModule());
    }


    private List<String> getServicePackages() throws Exception {
        List<String> servicePackageNames = new ArrayList<>();
        ClassLoader classLoader = ServiceBoot.class.getClassLoader();
        File propertiesFile = Resources.getResourceAsFile(classLoader, FILE_PROP);
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(propertiesFile);
        properties.load(inputStream);
        Enumeration<?> propKeys = properties.propertyNames();
        while (propKeys.hasMoreElements()) {
            String key = propKeys.nextElement().toString();
            if (!key.startsWith(PREFIX_SERVICE_PKG))
                continue;
            String servicePackageName = properties.getProperty(key);
            LOGGER.debug("Cloud service package name: {}", servicePackageName);
            servicePackageNames.add(servicePackageName);
        }
        return servicePackageNames;
    }
}
