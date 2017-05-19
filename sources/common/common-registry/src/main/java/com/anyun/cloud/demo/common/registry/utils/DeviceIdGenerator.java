package com.anyun.cloud.demo.common.registry.utils;

import org.hashids.Hashids;

import java.util.UUID;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 2017/5/18
 */
public class DeviceIdGenerator {
    private static DeviceIdGenerator generator = null;

    private DeviceIdGenerator() {

    }

    public static DeviceIdGenerator getGenerator() {
        synchronized (DeviceIdGenerator.class) {
            if (generator == null)
                generator = new DeviceIdGenerator();
        }
        return generator;
    }

    public String generate(int length) {
        if (length < 4)
            length = 4;
        String salt = getUuidSalt();
        Hashids hashids = new Hashids(salt, length);
        String hash = hashids.encode(System.nanoTime());
        return hash;
    }

    public String generate() {
        return generate(8);
    }

    private String getUuidSalt() {
        return UUID.randomUUID().toString();
    }

}
