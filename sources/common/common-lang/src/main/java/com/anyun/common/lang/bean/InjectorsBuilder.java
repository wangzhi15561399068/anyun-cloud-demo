/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.anyun.common.lang.bean;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author TwitchGG <ray@proxzone.com>
 * @since 1.0.0 on 9/27/16
 */
public class InjectorsBuilder {
    private static InjectorsBuilder builder;
    private Injector kernelInjector;

    private InjectorsBuilder() {

    }

    public static InjectorsBuilder getBuilder() {
        synchronized (InjectorsBuilder.class) {
            if (builder == null)
                builder = new InjectorsBuilder();
        }
        return builder;
    }

    public InjectorsBuilder build(AbstractModule... modules) {
        if (kernelInjector != null)
            kernelInjector = null;
        kernelInjector = Guice.createInjector(modules);
        return this;
    }

    public Injector getInjector() {
        return kernelInjector;
    }

    public <T> T getInstanceByType(Class<T> clazz) {
        return kernelInjector.getInstance(clazz);
    }
}