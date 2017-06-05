package com.anyun.cloud.demo.api.management.raml.api;

import java.util.LinkedList;
import java.util.List;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 31/05/2017
 */
public class ApiResourceEntity {
    private String path;
    private String name;
    private String desc;
    private String method;
    private List<ApiMethodParamEntity> parameters = new LinkedList<>();
    private ApiRequestBody requestBody;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<ApiMethodParamEntity> getParameters() {
        return parameters;
    }

    public void setParameters(List<ApiMethodParamEntity> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(ApiMethodParamEntity paramEntity) {
        parameters.add(paramEntity);
    }

    public ApiRequestBody getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(ApiRequestBody requestBody) {
        this.requestBody = requestBody;
    }
}
