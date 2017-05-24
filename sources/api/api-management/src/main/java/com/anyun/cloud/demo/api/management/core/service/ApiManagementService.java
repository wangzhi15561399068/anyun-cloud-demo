package com.anyun.cloud.demo.api.management.core.service;

import com.anyun.cloud.demo.api.management.entity.ApiStatusEntity;

import java.util.List;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 22/05/2017
 */
public interface ApiManagementService {

    /**
     *
     * @param raml
     * @throws Exception
     */
    String deploy(String raml) throws Exception;

    /**
     *
     * @param rid
     * @throws Exception
     */
    void destroy(String rid) throws Exception;

    /**
     *
     * @param rid
     * @throws Exception
     */
    void enable(String rid) throws Exception;

    /**
     *
     * @param rid
     * @throws Exception
     */
    void disable(String rid) throws Exception;

    /**
     *
     * @param rid
     * @return
     * @throws Exception
     */
    ApiStatusEntity getApiStatus(String rid) throws Exception;

    /**
     *
     * @return
     * @throws Exception
     */
    List<ApiStatusEntity> getApiStatues() throws Exception;

    /**
     *
     * @param query
     * @return
     * @throws Exception
     */
    List<ApiStatusEntity> queryApiStatues(String query) throws Exception;
}
