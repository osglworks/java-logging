package org.osgl.logging.service;

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
