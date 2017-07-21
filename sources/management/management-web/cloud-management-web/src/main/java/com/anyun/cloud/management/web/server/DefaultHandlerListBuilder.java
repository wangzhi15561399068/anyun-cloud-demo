package com.anyun.cloud.management.web.server;

import com.anyun.cloud.management.web.common.ResourceFilter;
import com.anyun.cloud.management.web.common.thymeleaf.ThymesTemplateFilter;
import com.anyun.cloud.management.web.common.HandlerListBuilder;
import com.anyun.common.lang.options.ApplicationOptions;
import com.google.inject.Inject;
import org.apache.commons.cli.CommandLine;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.Filter;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 21/06/2017
 */
public class DefaultHandlerListBuilder implements HandlerListBuilder {
    private HandlerList handlerList;
    private CommandLine cl;
    private Filter thymesTemplateFilter;
    private Filter resourceFilter;

    @Inject
    public DefaultHandlerListBuilder(ApplicationOptions options,
                                     @ThymesTemplateFilter() Filter thymesTemplateFilter,
                                     @ResourceFilter Filter resourceFilter) {
        this.cl = options.getCommandLine();
        this.thymesTemplateFilter = thymesTemplateFilter;
        this.resourceFilter = resourceFilter;
        handlerList = new HandlerList();
    }

    /**
     * public void addFilter(FilterHolder holder, String pathSpec, EnumSet<DispatcherType> dispatches) {
     this.getServletHandler().addFilterWithMapping(holder, pathSpec, dispatches);
     }
     public void addHandler(Handler handler) {
     this.setHandlers((Handler[])ArrayUtil.addToArray(this.getHandlers(), handler, Handler.class));
     }
     * @return
     * @throws Exception
     */
    @Override
    public HandlerList build() throws Exception {
        ServletContextHandler context = new ServletContextHandler();
        context.addFilter(new FilterHolder(thymesTemplateFilter), "/*", null);
        context.addFilter(new FilterHolder(resourceFilter), "/*", null);
        handlerList.addHandler(context);
        return handlerList;
    }
}
