package com.anyun.common.service.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 12/06/2017
 */
public class DefaultServiceDeployer implements ServiceDeployer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultServiceDeployer.class);

    @Override
    public void deploy(Class<? extends Service> aClass) throws Exception {

    }
}
