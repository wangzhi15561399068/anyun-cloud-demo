package com.anyun.cloud.management.web.server;

import com.anyun.cloud.management.web.common.ResourceResolver;
import com.anyun.cloud.management.web.common.thymeleaf.ThymeleafContext;
import com.anyun.common.lang.bean.InjectorsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 22/06/2017
 */
public class DefaultResourceFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultResourceFilter.class);
    private ThymeleafContext thymeleafContext;
    private ResourceResolver resourceResolver;
    private static final String RESOURCE_JS = ".js";
    private static final String RESOURCE_CSS = ".css";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        thymeleafContext = InjectorsBuilder.getBuilder().getInstanceByType(ThymeleafContext.class);
        this.resourceResolver = thymeleafContext.getResourceResolver();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!isResource(request)) {
            chain.doFilter(request, response);
            return;
        }
        String resource = resourceResolver.resolve((HttpServletRequest) request);
    }

    @Override
    public void destroy() {

    }

    private boolean isResource(ServletRequest request) {
        String requestURI = ((HttpServletRequest) request).getRequestURI();
        String controllerResource = requestURI;
        if (requestURI.endsWith(RESOURCE_JS) || requestURI.endsWith(RESOURCE_CSS))
            controllerResource = requestURI.substring(0, requestURI.length() - RESOURCE_CSS.length() - RESOURCE_JS.length());
        LOGGER.debug("Resolve controller by controller resource: {}", controllerResource);
            return true;
    }


}
