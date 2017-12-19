package org.osgl.logging.service;

/*-
 * #%L
 * OSGL Logging
 * %%
 * Copyright (C) 2017 OSGL (Open Source General Library)
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.osgl.logging.LogService;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Logger;
import org.pmw.tinylog.Level;

public class TinyLogService implements LogService {

    protected int level;
    private String name;

    public TinyLogService(Class<?> clz) {
        this.name = clz.getName();
        level = Logger.getLevel(name).ordinal();
    }

    public TinyLogService(String name) {
        this.name = name;
        level = Logger.getLevel(name).ordinal();
    }

    @Override
    public void setLevel(org.osgl.logging.Logger.Level level) {
        Level theirLevel = convert(level);
        Configurator.defaultConfig().level(name, theirLevel);
    }

    @Override
    public boolean isTraceEnabled() {
        return level <= Level.TRACE.ordinal();
    }

    @Override
    public void trace(String msg) {
        Logger.trace(msg);
    }

    @Override
    public void trace(Throwable t, String msg) {
        Logger.trace(t, msg);
    }

    @Override
    public boolean isDebugEnabled() {
        return level <= Level.DEBUG.ordinal();
    }

    @Override
    public void debug(String msg) {
        Logger.debug(msg);
    }

    @Override
    public void debug(Throwable t, String msg) {
        Logger.debug(t, msg);
    }

    @Override
    public boolean isInfoEnabled() {
        return level >= Level.INFO.ordinal();
    }

    @Override
    public void info(String msg) {
        Logger.info(msg);
    }

    @Override
    public void info(Throwable t, String msg) {
        Logger.info(t, msg);
    }

    @Override
    public boolean isWarnEnabled() {
        return level <= Level.WARNING.ordinal();
    }

    @Override
    public void warn(String msg) {
        Logger.warn(msg);
    }

    @Override
    public void warn(Throwable t, String msg) {
        Logger.warn(t, msg);
    }

    @Override
    public boolean isErrorEnabled() {
        return level <= Level.ERROR.ordinal();
    }

    @Override
    public void error(String msg) {
        Logger.error(msg);
    }

    @Override
    public void error(Throwable t, String msg) {
        Logger.error(t, msg);
    }

    @Override
    public void fatal(String msg) {
        Logger.error(msg);
    }

    @Override
    public void fatal(Throwable t, String msg) {
        Logger.error(t, msg);
    }

    private Level convert(org.osgl.logging.Logger.Level level) {
        switch (level) {
            case TRACE:
                return Level.TRACE;
            case DEBUG:
                return Level.DEBUG;
            case INFO:
                return Level.INFO;
            case WARN:
                return Level.WARNING;
            default:
                return Level.ERROR;
        }
    }
}
