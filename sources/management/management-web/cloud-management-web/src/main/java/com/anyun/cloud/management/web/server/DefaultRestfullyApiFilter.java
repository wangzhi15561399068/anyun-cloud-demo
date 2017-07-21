package com.anyun.cloud.management.web.server;

import com.anyun.common.lang.bean.InjectorsBuilder;
import com.google.inject.Key;
import com.google.inject.name.Names;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 22/07/2017
 */
public class DefaultRestfullyApiFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRestfullyApiFilter.class);
    private ResourceHandler apiResourceHandler;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.apiResourceHandler = InjectorsBuilder.getBuilder().getInjector()
                .getInstance(Key.get(ResourceHandler.class, Names.named("api")));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (apiResourceHandler.isResource(request)) {
            apiResourceHandler.process(request, response);
        } else {
            chain.doFilter(request, response);
            return;
        }
    }

    @Override
    public void destroy() {

    }

}
