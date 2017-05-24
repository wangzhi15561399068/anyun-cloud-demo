package com.anyun.cloud.demo.api.management.raml;

import org.raml.v2.api.RamlModelBuilder;
import org.raml.v2.api.RamlModelResult;
import org.raml.v2.api.model.v10.api.Api;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 23/05/2017
 */
public interface RamlApiRamlParser {

    /**
     *
     * @param raml
     * @return
     */
    DemoApiRamlParser withRamlString(String raml);

    /**
     *
     * @param encoding
     * @return
     */
    DemoApiRamlParser withEncoding(String encoding);

    /**
     *
     * @return
     * @throws Exception
     */
    Api buildV10Api() throws Exception;

    /**
     *
     * @return
     * @throws Exception
     */
    RamlApiEntity buildApi() throws Exception;
}
