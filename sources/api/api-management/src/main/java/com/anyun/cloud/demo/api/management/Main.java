package com.anyun.cloud.demo.api.management;

import com.anyun.cloud.demo.api.management.core.module.CommonBindingModule;
import com.anyun.cloud.demo.api.management.core.module.EtcdApiClientBindingModule;
import com.anyun.cloud.demo.api.management.core.module.HttpApiServerBindingModule;
import com.anyun.cloud.demo.api.management.core.module.ServiceBindingModule;
import com.anyun.cloud.demo.api.management.http.ManagementApiServer;
import com.anyun.cloud.demo.common.registry.service.RegistryBindingModule;
import com.anyun.common.lang.bean.InjectorsBuilder;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 22/05/2017
 */
public class Main {
    public static void main(String[] args) throws Exception {
        InjectorsBuilder.getBuilder().build(
                new CommonBindingModule(),
                new EtcdApiClientBindingModule(),
                new RegistryBindingModule(),
                new HttpApiServerBindingModule(),
                new ServiceBindingModule());
        ManagementApiServer server = InjectorsBuilder.getBuilder().getInstanceByType(ManagementApiServer.class);
        server.start();
    }
}
