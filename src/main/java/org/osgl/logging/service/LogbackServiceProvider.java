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

import ch.qos.logback.classic.LoggerContext;
import org.osgl.$;
import org.osgl.logging.LogService;
import org.osgl.logging.LogServiceProvider;
import org.osgl.util.E;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackServiceProvider implements LogServiceProvider {

    private LoggerContext loggerContext;

    public LogbackServiceProvider() {
        Logger testlogger = LoggerFactory.getLogger(LogbackServiceProvider.class);
        E.unexpectedIf(!(testlogger instanceof ch.qos.logback.classic.Logger), "sl4j not return logback logger");
    }

    @Override
    public LogService getLogService(String name) {
        return new LogbackService((ch.qos.logback.classic.Logger)LoggerFactory.getLogger(name));
    }
}
