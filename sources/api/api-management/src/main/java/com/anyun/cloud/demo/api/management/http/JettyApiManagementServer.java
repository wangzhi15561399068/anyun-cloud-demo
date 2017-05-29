package com.anyun.cloud.demo.api.management.http;

import com.anyun.cloud.demo.api.management.core.distributed.management.DistributedManagementService;
import com.anyun.cloud.demo.api.management.module.HttpApiServerBindingModule;
import com.anyun.common.lang.http.AbstractJettyApiServer;
import com.anyun.common.lang.zookeeper.ZookeeperClient;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 29/05/2017
 */
@Singleton
public class JettyApiManagementServer extends AbstractJettyApiServer {
    private DistributedManagementService distributedManagementService;

    @Inject
    public JettyApiManagementServer(ZookeeperClient zookeeperClient,
                                    DistributedManagementService distributedManagementService,
                                    @Named(HttpApiServerBindingModule.NAMED_MGR_SERVLET_HANDLER) ServletHandler apiHandler,
                                    @Named(HttpApiServerBindingModule.NAMED_MGR_SERVLET) Class apiProcessServlet) {
        super(zookeeperClient, apiHandler, apiProcessServlet);
        this.distributedManagementService = distributedManagementService;
    }

    @Override
    protected void initServerConfig() throws Exception {
        ApiManagementServerConfigEntity config = distributedManagementService.getManagementApiServerConfig();
        setConfig(config.asConfig());
    }

    @Override
    protected void registToCluster() throws Exception {
        distributedManagementService.regist();
    }
}
