package org.osgl.logging.service;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.osgl.logging.LogService;

/**
 * Created by luog on 15/02/14.
 */
public class Log4jService implements LogService {

    protected final org.apache.log4j.Logger logger;

    public Log4jService(Class<?> clz) {
        logger = Logger.getLogger(clz);
    }

    public Log4jService(String name) {
        this.logger = Logger.getLogger(name);
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
        return logger.isEnabledFor(Level.WARN);
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
        return logger.isEnabledFor(Level.ERROR);
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
        logger.fatal(msg);
    }

    @Override
    public void fatal(Throwable t, String msg) {
        logger.fatal(msg, t);
    }
}
