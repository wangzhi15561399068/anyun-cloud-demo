package com.anyun.common.service.exchange;

import com.anyun.common.service.context.SessionContext;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 12/06/2017
 */
public class DefaultExchange implements Exchange {
    private SessionContext sessionContext;
    private ExchangeBond inBond;
    private ExchangeBond outExchangeBond;

    @Override
    public ExchangeBond getIn() {
        return null;
    }

    @Override
    public SessionContext getSessionContext() {
        return null;
    }
}
