package com.anyun.cloud.management.web.template;

import com.anyun.cloud.management.web.config.WebServerOptions;
import com.anyun.cloud.management.web.controller.ThymeleafController;
import com.anyun.cloud.management.web.controller.ThymeleafControllerResolver;
import com.anyun.cloud.management.web.resource.ResourceResolver;
import com.anyun.common.lang.options.ApplicationOptions;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.Writer;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 21/06/2017
 */
@Singleton
public class DefaultThymeleafContext implements ThymeleafContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultThymeleafContext.class);
    private FileTemplateResolver templateResolver;
    private ThymeleafControllerResolver controllerResolver;
    private TemplateEngine templateEngine;
    private ResourceResolver resourceResolver;

    @Inject
    public DefaultThymeleafContext(ApplicationOptions options,
                                   ThymeleafControllerResolver controllerResolver,
                                   ResourceResolver resourceResolver) throws Exception {
        this.controllerResolver = controllerResolver;
        this.resourceResolver = resourceResolver;
        templateResolver = new FileTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.HTML);
        if (!options.getCommandLine().hasOption(WebServerOptions.WEB_DEPLOY_DIR))
            throw new Exception("Option [" + WebServerOptions.WEB_DEPLOY_DIR + "] not set");
        templateResolver.setPrefix(options.getCommandLine().getOptionValue(
                WebServerOptions.WEB_DEPLOY_DIR) + "/" + DIR_TEMPLATE);
        templateResolver.setSuffix(".html");
        if (options.getCommandLine().hasOption(WebServerOptions.TEMPLATE_CACHE_TTL)) {
            templateResolver.setCacheable(true);
            templateResolver.setCacheTTLMs(Long.valueOf(options.getCommandLine().getOptionValue(
                    WebServerOptions.TEMPLATE_CACHE_TTL)));
        } else
            templateResolver.setCacheable(false);
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    @Override
    public ThymeleafController resolveControllerForRequest(HttpServletRequest request) {
        return controllerResolver.resolve(request);
    }

    @Override
    public ResourceResolver getResourceResolver() {
        return resourceResolver;
    }

    @Override
    public void templateProcess(WebContext context, String templateURI, Writer writer) throws Exception {
        try {
            templateEngine.process(templateURI, context, writer);
        } catch (Exception ex) {
            LOGGER.error("Template engine process error: {}", ex.getMessage(), ex);
            throw new Exception("Template engine process error");
        }
    }
}
