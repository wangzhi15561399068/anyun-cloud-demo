package com.anyun.example.service.impl;

import com.anyun.common.lang.StringUtils;
import com.anyun.common.service.annotation.CloudService;
import com.anyun.common.service.common.Service;
import com.anyun.common.service.exchange.Exchange;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 06/06/2017
 */
@CloudService(servicePath = "/app1/api/v1_0_0/api1")
public class ExampleService1 implements Service<List> {

    @Override
    public List<String> onExchange(Exchange exchange) {
        return Arrays.asList(StringUtils.formatDate(new Date(), null)
                , StringUtils.formatDate(new Date(), null)
                , StringUtils.formatDate(new Date(), null)
                , StringUtils.formatDate(new Date(), null)
                , StringUtils.formatDate(new Date(), null));
    }
}
