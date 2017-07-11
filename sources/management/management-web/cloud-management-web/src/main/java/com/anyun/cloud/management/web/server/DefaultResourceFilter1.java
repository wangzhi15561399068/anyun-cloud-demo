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
 * Created by wz on 2017/7/11.
 */
public class DefaultResourceFilter1 implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultResourceFilter1.class);
    private ThymeleafContext thymeleafContext;
    private ResourceResolver resourceResolver;
    private static final String RESOURCE_PNG = ".png";
    private static final String RESOURCE_GIF = ".gif";
    private static final String RESOURCE_JPG = ".jpg";
    private static final String RESOURCE_ICO = ".ico";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        thymeleafContext = InjectorsBuilder.getBuilder().getInstanceByType(ThymeleafContext.class);
        this.resourceResolver = thymeleafContext.getResourceResolver();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!isResourceImg(request)) {
            chain.doFilter(request, response);
            return;
        }
        String resource = resourceResolver.resolve((HttpServletRequest) request);
    }

    @Override
    public void destroy() {

    }
    private boolean isResourceImg(ServletRequest request) {
        String requestURI = ((HttpServletRequest) request).getRequestURI();
        String controllerResource = requestURI;
        if (requestURI.endsWith(RESOURCE_JPG) || requestURI.endsWith(RESOURCE_PNG) || requestURI.endsWith(RESOURCE_GIF) || requestURI.endsWith(RESOURCE_ICO))
            controllerResource = requestURI.substring(0, requestURI.length() - RESOURCE_JPG.length() - RESOURCE_PNG.length() - RESOURCE_GIF.length() - RESOURCE_ICO.length());
        LOGGER.debug("Resolve controller by controller resource: {}", controllerResource);
        return true;
    }
}
