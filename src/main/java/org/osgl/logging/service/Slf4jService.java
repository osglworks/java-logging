package org.osgl.logging.service;

import org.osgl.logging.LogService;
import org.osgl.util.E;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.NOPLogger;

public class Slf4jService implements LogService {
    protected final Logger logger;

    public Slf4jService(Class clazz) {
        logger = LoggerFactory.getLogger(clazz);
        ensureLogger();
    }

    public Slf4jService(String name) {
        logger = LoggerFactory.getLogger(name);
        ensureLogger();
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        logger.trace(msg);
    }

    @Override
    public void trace(Throwable t, String msg) {
        logger.trace(msg, t);
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        logger.debug(msg);
    }

    @Override
    public void debug(Throwable t, String msg) {
        logger.debug(msg, t);
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        logger.info(msg);
    }

    @Override
    public void info(Throwable t, String msg) {
        logger.info(msg, t);
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    public void warn(String msg) {
        logger.warn(msg);
    }

    @Override
    public void warn(Throwable t, String msg) {
        logger.warn(msg, t);
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    public void error(String msg) {
        logger.error(msg);
    }

    @Override
    public void error(Throwable t, String msg) {
        logger.error(msg, t);
    }

    @Override
    public void fatal(String msg) {
        logger.error(msg);
    }

    @Override
    public void fatal(Throwable t, String msg) {
        logger.error(msg, t);
    }

    private void ensureLogger() {
        E.illegalStateIf(logger instanceof NOPLogger, "cannot initialize Slf4jService");
    }
}
