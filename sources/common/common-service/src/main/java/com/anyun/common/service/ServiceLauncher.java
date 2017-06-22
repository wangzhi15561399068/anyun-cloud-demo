package com.anyun.common.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 16/06/2017
 */
public class ServiceLauncher {
    public static void main(String[] args) throws Exception {
//        System.getenv().put("SERVICE_GIT_URL", "http://git.dev.hohhot.ga.gov/pgis/service-example1.git");
//        System.getenv().put("SERVICE_DEPLOY_DIR", "/Users/twitchgg/Develop/temp/hohot-cloud-demo/git");
        System.setProperty("SERVICE_GIT_URL", "http://git.dev.hohhot.ga.gov/pgis/service-example1.git");
        System.setProperty("SERVICE_DEPLOY_DIR", "/Users/twitchgg/Develop/temp/hohot-cloud-demo/git");
        ServiceBoot.boot(ServiceLauncher.class, args);
    }
}
