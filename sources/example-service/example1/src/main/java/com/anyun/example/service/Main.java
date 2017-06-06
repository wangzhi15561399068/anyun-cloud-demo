package com.anyun.example.service;

import com.anyun.common.service.CloudServiceStarter;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 06/06/2017
 */
public class Main {
    public static void main(String[] args) throws Exception {
        CloudServiceStarter.getStarter().start(Main.class);
    }
}
