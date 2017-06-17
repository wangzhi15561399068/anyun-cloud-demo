package com.anyun.common.service;

import com.anyun.cloud.demo.common.registry.client.RegisterClient;
import com.anyun.cloud.demo.common.registry.client.RegistryModule;
import com.anyun.cloud.demo.common.registry.client.RegistryOptions;
import com.anyun.cloud.demo.common.registry.entity.NodeType;
import com.anyun.cloud.service.common.Service;
import com.anyun.common.lang.bean.InjectorsBuilder;
import com.anyun.common.lang.msg.NatsClient;
import com.anyun.common.service.classloader.CloudServiceClassLoader;
import com.anyun.common.service.classloader.CloudServiceClassLoaderBuilder;
import com.anyun.common.service.common.PackageScanClassResolver;
import com.anyun.common.service.common.ServiceCache;
import com.anyun.common.service.common.ServiceCommonModule;
import com.anyun.common.service.common.ServiceDeployer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 12/06/2017
 */
public class ServiceBoot {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceBoot.class);
    private PackageScanClassResolver resolver = new PackageScanClassResolver();
    private RegisterClient registerClient;

    private ServiceBoot() {
    }

    public static void boot(Class<?> clazz, String[] args) throws Exception {
        ServiceBoot boot = new ServiceBoot();
        boot.initGuiceService(args);
        boot.registerClient = InjectorsBuilder.getBuilder().getInstanceByType(RegisterClient.class);
        CloudServiceClassLoader classLoader = new CloudServiceClassLoaderBuilder()
                .withBuildBaseClass(clazz)
                .build();
        List<Class<? extends Service>> allCloudServiceClasses = classLoader.scan();
        ServiceCache serviceCache = InjectorsBuilder.getBuilder().getInstanceByType(ServiceCache.class);
        String deviceId = boot.registerClient.regist(Arrays.asList(NodeType.SERVICE_NODE));
        serviceCache.setDeviceId(deviceId);
        serviceCache.setCloudServiceClassLoader(classLoader);
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
}
