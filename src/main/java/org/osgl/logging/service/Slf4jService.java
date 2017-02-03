package org.osgl.logging.service;

import org.osgl.logging.LogService;
import org.osgl.util.E;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.NOPLogger;

public class Slf4jService implements LogService {
    protected final Logger logger;
    private org.osgl.logging.Logger.Level level;


    public Slf4jService(Class clazz) {
        logger = LoggerFactory.getLogger(clazz);
        ensureLogger();
    }

    public Slf4jService(String name) {
        logger = LoggerFactory.getLogger(name);
        ensureLogger();
    }

    @Override
    public void setLevel(org.osgl.logging.Logger.Level level) {
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
