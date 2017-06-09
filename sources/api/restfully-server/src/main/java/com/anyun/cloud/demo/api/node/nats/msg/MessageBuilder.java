package com.anyun.cloud.demo.api.node.nats.msg;

import io.nats.client.Message;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 09/06/2017
 */
public interface MessageBuilder {
    Message build();
}
