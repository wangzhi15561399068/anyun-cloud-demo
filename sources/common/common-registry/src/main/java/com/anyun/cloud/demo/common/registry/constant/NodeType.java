package com.anyun.cloud.demo.common.registry.constant;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 2017/5/18
 */
public enum NodeType {
    API_REST_NODE(1),
    SERVICE_NODE(2);

    private int value;

    NodeType(int value) {
        this.value = value;
    }
}
