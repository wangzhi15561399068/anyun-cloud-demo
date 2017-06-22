package com.anyun.cloud.management.web.controller;

import com.anyun.cloud.management.web.annotation.ThymesController;
import com.google.inject.Inject;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 22/06/2017
 */
public class DefaultThymeleafControllerResolver implements ThymeleafControllerResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultThymeleafControllerResolver.class);
    private List<String> controllerPackageNames;
    private Map<String, ThymeleafController> controllers;
    private ControllerPackageNames packageNames;
    private FastClasspathScanner scanner;

    @Inject
    public DefaultThymeleafControllerResolver(ControllerPackageNames packageNames) {
        this.packageNames = packageNames;
        controllerPackageNames = new ArrayList<>();
        controllers = new HashMap<>();
        scanner = new FastClasspathScanner();
        if (packageNames.getPackages().isEmpty()) {
            //add default controllers package of 'ControllerPackageNames.class.getPackage().getName()'
            controllerPackageNames.add(ControllerPackageNames.class.getPackage().getName());
        }
        scanner.addClassLoader(this.getClass().getClassLoader());
        scanner.matchClassesWithAnnotation(ThymesController.class,
                aClass -> Arrays.stream(aClass.getInterfaces()).filter(c -> {
                    if (!matchPackage(aClass))
                        return false;
                    ThymesController thymesController = aClass.getAnnotation(ThymesController.class);
                    LOGGER.debug("Resolve thymes controller: {}", thymesController);
                    String controllerMapping = thymesController.mapping();
                    try {
                        ThymeleafController controller = (ThymeleafController) aClass.newInstance();
                        if (controller == null)
                            return false;
                        controllers.put(controllerMapping, controller);
                        LOGGER.debug("Add thymes controller [{}] by URI [{}]", controller, controllerMapping);
                    } catch (Exception ex) {
                        return false;
                    }
                    return true;
                }).findAny());
        scanner.scan();
    }

    private boolean matchPackage(Class<?> aClass) {
        String classPackageName = aClass.getPackage().getName();
        for (String packageName : packageNames.getPackages()) {
            if (classPackageName.startsWith(packageName))
                return true;
        }
        return false;
    }

    @Override
    public ThymeleafController resolve(HttpServletRequest request) {
        LOGGER.debug("Resolve controller by request URI: {}", request.getRequestURI());
        return controllers.get(request.getRequestURI());
    }
}
