package com.anyun.example.service.impl;

import com.anyun.common.service.annotation.CloudService;
import com.anyun.common.service.common.Service;
import com.anyun.common.service.exchange.Exchange;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 06/06/2017
 */
@CloudService(servicePath = "/app1/api/v1_0_0/api1")
public class ExampleService1 implements Service {

    @Override
    public void onExchange(Exchange exchange) {

    }
}
