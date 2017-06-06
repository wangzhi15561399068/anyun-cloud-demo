package com.anyun.common.service;

import com.anyun.common.service.exchange.Exchange;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 06/06/2017
 */
public abstract class AbstractCloudService {

    public abstract void onExchange(Exchange exchange);
}
