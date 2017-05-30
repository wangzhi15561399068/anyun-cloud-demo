package com.anyun.cloud.demo.api.management.core.service.impl;

import com.anyun.cloud.demo.api.management.core.service.ApiManagementService;
import com.anyun.cloud.demo.api.management.core.service.ApiStatusEntity;

import java.util.List;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 22/05/2017
 */
public class DefaultApiManagementServiceImpl implements ApiManagementService {

    public DefaultApiManagementServiceImpl() {

    }

    @Override
    public String deploy(String raml) throws Exception {
        return null;
    }

    @Override
    public void destroy(String rid) throws Exception {

    }

    @Override
    public void enable(String rid) throws Exception {

    }

    @Override
    public void disable(String rid) throws Exception {

    }

    @Override
    public ApiStatusEntity getApiStatus(String rid) throws Exception {
        return null;
    }

    @Override
    public List<ApiStatusEntity> getApiStatues() throws Exception {
        return null;
    }

    @Override
    public List<ApiStatusEntity> queryApiStatues(String query) throws Exception {
        return null;
    }
}
