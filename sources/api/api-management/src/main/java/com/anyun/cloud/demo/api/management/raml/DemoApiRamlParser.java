package com.anyun.cloud.demo.api.management.raml;

import org.raml.v2.api.RamlModelBuilder;
import org.raml.v2.api.RamlModelResult;
import org.raml.v2.api.model.v10.api.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 23/05/2017
 */
public class DemoApiRamlParser implements RamlApiRamlParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoApiRamlParser.class);
    private static final String ENCODING = "raml.parser.encoding";
    private String ramlContent;

    @Inject
    public DemoApiRamlParser() {
        System.setProperty(ENCODING, "UTF-8");
    }

    @Override
    public DemoApiRamlParser withRamlString(String raml) {
        this.ramlContent = ramlContent;
        return this;
    }

    @Override
    public DemoApiRamlParser withEncoding(String encoding) {
        System.setProperty(ENCODING, encoding);
        return this;
    }

    @Override
    public Api buildV10Api() throws Exception {
        LOGGER.debug("RAML content [{}]", ramlContent);
        RamlModelResult ramlModelResult = new RamlModelBuilder().buildApi(ramlContent);
        Api api = ramlModelResult.getApiV10();
        return api;
    }

    @Override
    public RamlApiEntity buildApi() throws Exception {
        Api api = buildV10Api();
        RamlApiEntity entity = new RamlApiEntity();
        entity.setTitle(api.title().value());
        return entity;
    }
}
