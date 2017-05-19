package com.anyun.cloud.demo.common.registry.constant;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 2017/5/18
 */
public class NodeInformationEntity {
    @SerializedName("uid")
    private String uid;
    @SerializedName("node_type")
    private List<NodeType> nodeType;
    @SerializedName("sys_time")
    private long timestamp;
    @SerializedName("upstart_time")
    private long upstartTimestamp;
    @SerializedName("networks")
    private List<NodeNetworkInfoEntity> networks;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<NodeType> getNodeType() {
        return nodeType;
    }

    public void setNodeType(List<NodeType> nodeType) {
        this.nodeType = nodeType;
    }

    public long getUpstartTimestamp() {
        return upstartTimestamp;
    }

    public void setUpstartTimestamp(long upstartTimestamp) {
        this.upstartTimestamp = upstartTimestamp;
    }

    public List<NodeNetworkInfoEntity> getNetworks() {
        return networks;
    }

    public void setNetworks(List<NodeNetworkInfoEntity> networks) {
        this.networks = networks;
    }
}
