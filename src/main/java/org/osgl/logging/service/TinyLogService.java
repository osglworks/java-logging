package org.osgl.logging.service;

import org.osgl.logging.LogService;
import org.pmw.tinylog.Logger;
import org.pmw.tinylog.LoggingLevel;

/**
 * Created by luog on 15/02/14.
 */
public class TinyLogService implements LogService {

    protected int level;

    public TinyLogService(Class<?> clz) {
        level = Logger.getLoggingLevel(clz.getName()).ordinal();
    }

    public TinyLogService(String name) {
        level = Logger.getLoggingLevel(name).ordinal();
    }

    @Override
    public boolean isTraceEnabled() {
        return level <= LoggingLevel.TRACE.ordinal();
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
        return level <= LoggingLevel.DEBUG.ordinal();
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
        return level >= LoggingLevel.INFO.ordinal();
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
        return level <= LoggingLevel.WARNING.ordinal();
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
        return level <= LoggingLevel.ERROR.ordinal();
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
}
