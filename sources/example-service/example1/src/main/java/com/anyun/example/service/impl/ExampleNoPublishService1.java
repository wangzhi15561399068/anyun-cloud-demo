package com.anyun.example.service.impl;

import com.anyun.common.lang.StringUtils;
import com.anyun.common.service.annotation.CloudService;
import com.anyun.common.service.common.Service;
import com.anyun.common.service.context.ServiceContext;
import com.anyun.common.service.exchange.Exchange;

import java.util.Date;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 12/06/2017
 */
@CloudService(servicePath = "/np/app1/service1", publishApi = false)
public class ExampleNoPublishService1 implements Service<String> {

    @Override
    public String onExchange(Exchange exchange) {
        return "inner_service1@" + ServiceContext.getDeviceId() + " time: "+ StringUtils.formatDate(new Date(),null);
    }
}
