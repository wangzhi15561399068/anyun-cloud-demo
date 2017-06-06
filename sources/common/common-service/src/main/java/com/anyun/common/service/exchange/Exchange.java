package com.anyun.common.service.exchange;

import com.anyun.common.service.context.ServiceContext;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 06/06/2017
 */
public interface Exchange {
    ExchangeContent getIn();

    void setIn(ExchangeContent content);

    ExchangeContent getOut();

    void setOut(ExchangeContent content);

    ServiceContext getContext();
}
