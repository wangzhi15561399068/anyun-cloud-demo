package com.anyun.cloud.management.web.inject;

import com.anyun.cloud.management.web.annotation.ResourceFilter;
import com.anyun.cloud.management.web.annotation.ThymesTemplateFilter;
import com.anyun.cloud.management.web.controller.ControllerPackageNames;
import com.anyun.cloud.management.web.controller.DefaultThymeleafControllerResolver;
import com.anyun.cloud.management.web.controller.ThymeleafController;
import com.anyun.cloud.management.web.controller.ThymeleafControllerResolver;
import com.anyun.cloud.management.web.filter.DefaultResourceFilter;
import com.anyun.cloud.management.web.filter.DefaultThymesTemplateFilter;
import com.anyun.cloud.management.web.resource.DefaultResourceResolver;
import com.anyun.cloud.management.web.resource.ResourceResolver;
import com.anyun.cloud.management.web.template.DefaultThymeleafContext;
import com.anyun.cloud.management.web.template.ThymeleafContext;
import com.google.inject.AbstractModule;

import javax.servlet.Filter;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 21/06/2017
 */
public class ThymesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ThymeleafContext.class).to(DefaultThymeleafContext.class);
        configureFilters();
        configureControllers();
    }

    private void configureFilters() {
        bind(Filter.class)
                .annotatedWith(ThymesTemplateFilter.class).to(DefaultThymesTemplateFilter.class);
        bind(Filter.class).annotatedWith(ResourceFilter.class).to(DefaultResourceFilter.class);
    }

    private void configureControllers() {
        ControllerPackageNames packageNames = new ControllerPackageNames()
                .witchPackage(ThymeleafController.class);
        bind(ControllerPackageNames.class).toInstance(packageNames);
        bind(ThymeleafControllerResolver.class).to(DefaultThymeleafControllerResolver.class);
        bind(ResourceResolver.class).to(DefaultResourceResolver.class);
    }
}
