package test.com.anyun.cloud.management.web;

import com.anyun.cloud.management.web.common.WebAppConfigBuilder;
import com.anyun.cloud.management.web.common.ResourceFilter;
import com.anyun.cloud.management.web.common.thymeleaf.ThymeleafContext;
import com.anyun.cloud.management.web.common.thymeleaf.ThymesApplicationVariablesBuilder;
import com.anyun.cloud.management.web.common.thymeleaf.ThymesTemplateFilter;
import com.anyun.cloud.management.web.common.HandlerListBuilder;
import com.anyun.cloud.management.web.common.thymeleaf.ThymeleafControllerPackageNames;
import com.anyun.cloud.management.web.thymeleaf.*;
import com.anyun.cloud.management.web.common.thymeleaf.ThymeleafControllerResolver;
import com.anyun.cloud.management.web.server.*;
import com.anyun.cloud.management.web.server.DefaultResourceFilter;
import com.anyun.cloud.management.web.common.ResourceResolver;
import com.anyun.cloud.management.web.common.WebServer;
import com.anyun.common.lang.bean.InjectorsBuilder;
import com.anyun.common.lang.options.ApplicationOptions;
import com.google.inject.AbstractModule;

import javax.servlet.Filter;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 22/06/2017
 */
public class WebControllTest extends BaseTest {
    public static void main(String[] args) throws Exception {
        InjectorsBuilder.getBuilder().build(
                new TestThymesModule()
        );
        InjectorsBuilder.getBuilder().getInstanceByType(WebServer.class).start();
    }

    public static class TestThymesModule extends AbstractModule {
        String path = "/Users/twitchgg/Develop/Projects/hohot-cloud-demo/sources/management/management-web/cloud-management-web-class/target";
        private String[] args = new String[]{
                "-http_port", "8080",
                "-webapp_deploy", path
        };

        @Override
        protected void configure() {
            bind(ApplicationOptions.class).toInstance(new WebServerOptions(args));

            bind(WebAppConfigBuilder.class).to(DefaultWebAppConfigBuilder.class);
            bind(HandlerListBuilder.class).to(DefaultHandlerListBuilder.class);
            bind(WebServer.class).to(DefaultWebServer.class);


            bind(ThymeleafContext.class).to(DefaultThymeleafContext.class);

            bind(Filter.class).annotatedWith(ThymesTemplateFilter.class).to(DefaultThymesTemplateFilter.class);
            bind(Filter.class).annotatedWith(ResourceFilter.class).to(DefaultResourceFilter.class);

            ThymeleafControllerPackageNames packageNames = new ThymeleafControllerPackageNames()
                    .withPackage("com.anyun.cloud.management.web.controller");
            bind(ThymeleafControllerPackageNames.class).toInstance(packageNames);
            bind(ThymeleafControllerResolver.class).to(DefaultThymeleafControllerResolver.class);
            bind(ResourceResolver.class).to(DefaultResourceResolver.class);
            bind(ThymesApplicationVariablesBuilder.class).to(DefaultThymesApplicationVariablesBuilder.class);
            bind(ThymeleafControllerClassloaderBuilder.class).to(DefaultThymeleafControllerClassloaderBuilder.class);
        }
    }
}
