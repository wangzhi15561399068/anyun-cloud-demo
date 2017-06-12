package com.anyun.common.service.context;


import com.anyun.common.lang.bean.InjectorsBuilder;
import com.anyun.common.service.common.ServiceCache;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 06/06/2017
 */
public class ServiceContext {
    private static final ThreadLocal<SessionContext> sessionContext = new ThreadLocal<>();

    public static final SessionContext getSessionContext() {
        return sessionContext.get();
    }

    public static final void setSessionContext(SessionContext context) {
        sessionContext.set(context);
    }

    public static String getDeviceId() {
        return InjectorsBuilder.getBuilder().getInstanceByType(ServiceCache.class).getDeviceId();
    }
}
