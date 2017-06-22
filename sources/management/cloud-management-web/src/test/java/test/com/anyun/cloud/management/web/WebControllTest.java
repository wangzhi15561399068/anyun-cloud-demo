package test.com.anyun.cloud.management.web;

import com.anyun.cloud.management.web.annotation.ResourceFilter;
import com.anyun.cloud.management.web.annotation.ThymesTemplateFilter;
import com.anyun.cloud.management.web.config.*;
import com.anyun.cloud.management.web.controller.ControllerPackageNames;
import com.anyun.cloud.management.web.controller.DefaultThymeleafControllerResolver;
import com.anyun.cloud.management.web.controller.ThymeleafControllerResolver;
import com.anyun.cloud.management.web.filter.DefaultResourceFilter;
import com.anyun.cloud.management.web.filter.DefaultThymesTemplateFilter;
import com.anyun.cloud.management.web.resource.DefaultResourceResolver;
import com.anyun.cloud.management.web.resource.ResourceResolver;
import com.anyun.cloud.management.web.server.DefaultWebServer;
import com.anyun.cloud.management.web.server.WebServer;
import com.anyun.cloud.management.web.template.*;
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
        private String[] args = new String[]{
                "-http_port", "8080",
                "-webapp_deploy", "/Users/twitchgg/Develop/temp/hohot-cloud-demo/web"
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

            ControllerPackageNames packageNames = new ControllerPackageNames()
                    .witchPackage(WebControllTest.class);
            bind(ControllerPackageNames.class).toInstance(packageNames);
            bind(ThymeleafControllerResolver.class).to(DefaultThymeleafControllerResolver.class);
            bind(ResourceResolver.class).to(DefaultResourceResolver.class);
            bind(ThymesApplicationVariablesBuilder.class).to(DefaultThymesApplicationVariablesBuilder.class);
        }
    }
}
