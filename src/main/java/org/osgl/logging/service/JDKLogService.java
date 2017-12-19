/*
 * Copyright (C) 2013 The Rythm Engine project
 * Gelin Luo <greenlaw110(at)gmail.com>
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
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
import org.osgl.logging.LogServiceProvider;
import org.osgl.logging.Logger;

import java.util.logging.Level;

import static java.util.logging.Level.*;

/**
 * A JDK logger implementation
 * Created by luog on 15/02/14.
 */
public class JDKLogService implements LogService {

    private static final long serialVersionUID = 1L;
    protected final java.util.logging.Logger logger;
    protected final String cname;

    public JDKLogService(Class c) {
        cname = c.getName();
        logger = java.util.logging.Logger.getLogger(cname);
    }

    public JDKLogService(String name) {
        cname = name;
        logger = java.util.logging.Logger.getLogger(name);
    }

    @Override
    public void setLevel(Logger.Level level) {
        logger.setLevel(convert(level));
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isLoggable(FINEST);
    }

    @Override
    public void trace(String msg) {
        log(FINEST, msg);
    }

    @Override
    public void trace(Throwable t, String msg) {
        log(FINEST, t, msg);
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isLoggable(FINE);
    }

    @Override
    public void debug(String msg) {
        log(FINE, msg);
    }

    @Override
    public void debug(Throwable t, String msg) {
        log(FINE, t, msg);
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isLoggable(INFO);
    }

    @Override
    public void info(String msg) {
        log(INFO, msg);
    }

    @Override
    public void info(Throwable t, String msg) {
        log(INFO, t, msg);
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isLoggable(WARNING);
    }

    @Override
    public void warn(String msg) {
        log(WARNING, msg);
    }

    @Override
    public void warn(Throwable t, String format) {
        log(WARNING, t, format);
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isLoggable(SEVERE);
    }

    @Override
    public void error(String format) {
        log(SEVERE, format);
    }

    @Override
    public void error(Throwable t, String msg) {
        log(SEVERE, t, msg);
    }

    @Override
    public void fatal(String msg) {
        log(SEVERE, msg);
    }

    @Override
    public void fatal(Throwable t, String msg) {
        log(SEVERE, t, msg);
    }

    protected void log(Level l, Throwable t, String m) {
        Throwable dummyException = new Throwable();
        StackTraceElement locations[] = dummyException.getStackTrace();
        String method = "unknown";
        if( locations != null && locations.length > 4 ) {
            StackTraceElement caller = locations[4];
            method = caller.getMethodName();
        }
        logger.logp(l, cname, method, m, t);
    }

    protected void log(Level l, String m) {
        Throwable dummyException = new Throwable();
        StackTraceElement locations[] = dummyException.getStackTrace();
        String method = "unknown";
        if( locations != null && locations.length > 6 ) {
            StackTraceElement caller = locations[6];
            method = caller.getMethodName();
        }
        logger.logp(l, cname, method, m);
    }

    private Level convert(Logger.Level level) {
        switch (level) {
            case TRACE:
                return Level.FINEST;
            case DEBUG:
                return Level.FINE;
            case INFO:
                return Level.INFO;
            case WARN:
                return Level.WARNING;
            case ERROR:
                return Level.SEVERE;
            default:
                return Level.SEVERE;
        }
    }

    public static class Factory implements LogServiceProvider {
        @Override
        public LogService getLogService(String name) {
            return new JDKLogService(name);
        }
    }
}
