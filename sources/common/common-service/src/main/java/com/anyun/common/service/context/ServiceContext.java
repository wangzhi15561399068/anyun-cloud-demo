package com.anyun.common.service.context;

import com.anyun.common.service.exchange.Exchange;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 06/06/2017
 */
public interface ServiceContext {
    Exchange getExchange();

    void route(String name);
}
