package com.anyun.common.service.exchange;

import com.anyun.common.service.context.SessionContext;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 06/06/2017
 */
public interface Exchange {

    /**
     * @return
     */
    ExchangeBond getIn();

    /**
     * @return
     */
    SessionContext getSessionContext();
}
