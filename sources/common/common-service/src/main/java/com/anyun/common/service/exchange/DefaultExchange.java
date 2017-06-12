package com.anyun.common.service.exchange;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 12/06/2017
 */
public class DefaultExchange implements Exchange {
    private ExchangeBond inBond;

    public DefaultExchange(ExchangeBond inBond) {
        this.inBond = inBond;
    }

    @Override
    public ExchangeBond getIn() {
        return inBond;
    }

}
