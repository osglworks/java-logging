package org.osgl.logging.service;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.osgl.logging.LogService;

public class LogbackService implements LogService {

    protected final Logger logger;

    public LogbackService(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void setLevel(org.osgl.logging.Logger.Level level) {
        logger.setLevel(convert(level));
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
        logger.error(msg);
    }

    @Override
    public void fatal(Throwable t, String msg) {
        logger.error(msg, t);
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
                return Level.WARN;
            default:
                return Level.ERROR;
        }
    }
}
