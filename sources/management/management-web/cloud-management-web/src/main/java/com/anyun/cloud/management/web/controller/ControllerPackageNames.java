package com.anyun.cloud.management.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 22/06/2017
 */
public class ControllerPackageNames {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerPackageNames.class);
    private List<String> packages = new ArrayList<>();

    public ControllerPackageNames(List<String> packages) {
        this.packages = packages;
    }


    public ControllerPackageNames() {
    }

    public ControllerPackageNames withPackage(String packageName) {
        packages.add(packageName);
        LOGGER.debug("Add controller resolve parent package: {}", packageName);
        return this;
    }

    public ControllerPackageNames witchPackage(Class<?> aClass) {
        return withPackage(aClass.getPackage().getName());
    }

    public List<String> getPackages() {
        return packages;
    }

    public void setPackages(List<String> packages) {
        this.packages = packages;
    }
}
