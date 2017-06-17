package com.anyun.common.service;

import com.anyun.common.service.common.Resources;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 16/06/2017
 */
public class ServiceLauncher {
    public static void main(String[] args) throws Exception {
        System.out.println(Resources.getRunDirectoyPath(ServiceLauncher.class));
        ServiceBoot.boot(ServiceLauncher.class, args);
    }
}
