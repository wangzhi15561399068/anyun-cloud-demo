package com.anyun.common.service.context;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 12/06/2017
 */
public class DefaultSessionContext implements SessionContext {
    private Router router;

    public DefaultSessionContext(Router router) {
        this.router = router;
    }

    @Override
    public Router getRouter() {
        return router;
    }
}
