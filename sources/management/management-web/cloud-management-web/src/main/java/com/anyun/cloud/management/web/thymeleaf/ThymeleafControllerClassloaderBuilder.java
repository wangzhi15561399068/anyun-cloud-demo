package com.anyun.cloud.management.web.thymeleaf;

import com.anyun.cloud.management.web.thymeleaf.ThymeleafControllerClassloader;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 23/06/2017
 */
public interface ThymeleafControllerClassloaderBuilder {
    ThymeleafControllerClassloader build() throws Exception;
}
