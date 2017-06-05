package com.anyun.cloud.demo.api.management.raml.api;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 01/06/2017
 */
public class ApiRequestBody {
    private String contentType;
    private ApiTypeEntity apiTypeEntity;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public ApiTypeEntity getApiTypeEntity() {
        return apiTypeEntity;
    }

    public void setApiTypeEntity(ApiTypeEntity apiTypeEntity) {
        this.apiTypeEntity = apiTypeEntity;
    }
}
