package com.anyun.cloud.management.web.common;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 21/06/2017
 */
public interface WebServer {
    void start() throws Exception;

    void stop() throws Exception;

    default void restart() throws Exception {
        stop();
        start();
    }
}
