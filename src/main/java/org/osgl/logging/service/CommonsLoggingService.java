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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgl.logging.LogService;
import org.osgl.logging.Logger;

public class CommonsLoggingService implements LogService {
    protected final Log logger;
    private Logger.Level level;

    public CommonsLoggingService(Class<?> clz) {
        logger = LogFactory.getLog(clz);
    }

    public CommonsLoggingService(String name) {
        logger = LogFactory.getLog(name);
    }

    @Override
    public void setLevel(Logger.Level level) {
        this.level = level;
    }

    @Override
    public boolean isTraceEnabled() {
        return (null != level && level.isTraceEnabled()) || logger.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        if (isTraceEnabled()) {
            logger.trace(msg);
        }
    }

    @Override
    public void trace(Throwable t, String msg) {
        if (isTraceEnabled()) {
            logger.trace(msg, t);
        }
    }

    @Override
    public boolean isDebugEnabled() {
        return (null != level && level.isDebugEnabled()) || logger.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        if (isDebugEnabled()) {
            logger.debug(msg);
        }
    }

    @Override
    public void debug(Throwable t, String msg) {
        if (isDebugEnabled()) {
            logger.debug(msg, t);
        }
    }

    @Override
    public boolean isInfoEnabled() {
        return (null != level && level.isInfoEnabled()) || logger.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        if (isInfoEnabled()) {
            logger.info(msg);
        }
    }

    @Override
    public void info(Throwable t, String msg) {
        if (isInfoEnabled()) {
            logger.info(msg, t);
        }
    }

    @Override
    public boolean isWarnEnabled() {
        return (null != level && level.isWarnEnabled()) || logger.isWarnEnabled();
    }

    @Override
    public void warn(String msg) {
        if (isWarnEnabled()) {
            logger.warn(msg);
        }
    }

    @Override
    public void warn(Throwable t, String msg) {
        if (isWarnEnabled()) {
            logger.warn(msg, t);
        }
    }

    @Override
    public boolean isErrorEnabled() {
        return (null != level && level.isErrorEnabled()) || logger.isErrorEnabled();
    }

    @Override
    public void error(String msg) {
        if (isErrorEnabled()) {
            logger.error(msg);
        }
    }

    @Override
    public void error(Throwable t, String msg) {
        if (isErrorEnabled()) {
            logger.error(msg, t);
        }
    }

    @Override
    public void fatal(String msg) {
        logger.fatal(msg);
    }

    @Override
    public void fatal(Throwable t, String msg) {
        logger.fatal(msg, t);
    }

}
