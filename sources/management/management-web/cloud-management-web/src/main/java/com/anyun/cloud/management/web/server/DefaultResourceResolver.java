package com.anyun.cloud.management.web.server;

import com.anyun.cloud.management.web.common.ResourceResolver;
import com.anyun.common.lang.options.ApplicationOptions;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 22/06/2017
 */
public class DefaultResourceResolver implements ResourceResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultResourceResolver.class);
    private static final String DIR_RESOURCE = "resources";
    private ApplicationOptions options;
    private String resourcePath;

    @Inject
    public DefaultResourceResolver(ApplicationOptions options) {
        this.options = options;
        this.resourcePath = options.getCommandLine().getOptionValue(
                WebServerOptions.WEB_DEPLOY_DIR) + "/" + DIR_RESOURCE;
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
