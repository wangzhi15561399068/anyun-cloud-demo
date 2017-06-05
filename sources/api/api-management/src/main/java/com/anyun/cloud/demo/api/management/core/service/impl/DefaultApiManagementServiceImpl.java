package com.anyun.cloud.demo.api.management.core.service.impl;

import com.anyun.cloud.demo.api.management.core.service.ApiManagementService;
import com.anyun.cloud.demo.api.management.core.service.ApiStatusEntity;
import com.anyun.cloud.demo.api.management.raml.RamlApiRamlParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 22/05/2017
 */
public class DefaultApiManagementServiceImpl implements ApiManagementService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiManagementService.class);
    private RamlApiRamlParser ramlApiRamlParser;

    public DefaultApiManagementServiceImpl(RamlApiRamlParser ramlApiRamlParser) {
        ramlApiRamlParser = ramlApiRamlParser;
    }

    @Override
    public String deploy(String zipFileName) throws Exception {
        //        String raml = RequestUtil.getPostBody(request);
//        LOGGER.debug("Deploy RAML body [\n{}\n]", raml);
//        DefaultResponseEntity responseEntity = new DefaultResponseEntity();
//        Api api = null;
//        try {
//            api = ramlApiRamlParser.buildV10Api();
//            if (api == null)
//                throw new Exception("Bad RAML format");
//        } catch (Exception ex) {
//            responseEntity.setCode(500);
//            responseEntity.setMessage(ex.getMessage());
//        }
//        return responseEntity;
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
