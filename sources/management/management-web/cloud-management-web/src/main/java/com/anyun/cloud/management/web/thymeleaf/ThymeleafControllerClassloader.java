package com.anyun.cloud.management.web.thymeleaf;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 23/06/2017
 */
public class ThymeleafControllerClassloader extends URLClassLoader {
    public ThymeleafControllerClassloader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public ThymeleafControllerClassloader(URL[] urls) {
        super(urls);
    }

    public ThymeleafControllerClassloader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }
}
