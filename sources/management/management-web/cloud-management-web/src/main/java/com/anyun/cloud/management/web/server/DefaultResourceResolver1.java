package com.anyun.cloud.management.web.server;

import com.anyun.cloud.management.web.common.ResourceResolver;
import com.anyun.common.lang.options.ApplicationOptions;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by wz on 2017/7/11.
 */
public class DefaultResourceResolver1 implements ResourceResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultResourceResolver1.class);
    private static final String DIR_RESOURCE_IMG = "resourcesImg";
    private ApplicationOptions options;
    private String resourcePath;

    @Inject
    public DefaultResourceResolver1(ApplicationOptions options) {
        this.options = options;
        this.resourcePath = ("/" + DIR_RESOURCE_IMG);
    }

    @Override
    public String resolve(HttpServletRequest request) {
        String resourceURI = request.getRequestURI();
        File resourceFile = new File(resourcePath + resourceURI);
        LOGGER.debug("Resource URI: {} {}", resourceFile.exists(), resourceFile.getAbsolutePath());
        if (!resourceFile.exists())
            return null;
        return null;
    }
}
