package com.anyun.cloud.management.web;

import com.anyun.cloud.management.web.common.ResourceFilter;
import com.anyun.cloud.management.web.common.thymeleaf.ThymesTemplateFilter;
import com.anyun.cloud.management.web.common.thymeleaf.ThymeleafControllerPackageNames;
import com.anyun.cloud.management.web.thymeleaf.DefaultThymeleafControllerResolver;
import com.anyun.cloud.management.web.common.thymeleaf.ThymeleafController;
import com.anyun.cloud.management.web.common.thymeleaf.ThymeleafControllerResolver;
import com.anyun.cloud.management.web.thymeleaf.DefaultResourceFilter;
import com.anyun.cloud.management.web.thymeleaf.DefaultThymesTemplateFilter;
import com.anyun.cloud.management.web.server.DefaultResourceResolver;
import com.anyun.cloud.management.web.common.ResourceResolver;
import com.anyun.cloud.management.web.thymeleaf.DefaultThymeleafContext;
import com.anyun.cloud.management.web.thymeleaf.DefaultThymesApplicationVariablesBuilder;
import com.anyun.cloud.management.web.common.thymeleaf.ThymeleafContext;
import com.anyun.cloud.management.web.common.thymeleaf.ThymesApplicationVariablesBuilder;
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
        ThymeleafControllerPackageNames packageNames = new ThymeleafControllerPackageNames()
                .witchPackage(ThymeleafController.class);
        bind(ThymeleafControllerPackageNames.class).toInstance(packageNames);
        bind(ThymeleafControllerResolver.class).to(DefaultThymeleafControllerResolver.class);
        bind(ResourceResolver.class).to(DefaultResourceResolver.class);
        bind(ThymesApplicationVariablesBuilder.class).to(DefaultThymesApplicationVariablesBuilder.class);
    }
}
