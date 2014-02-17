package org.osgl.logging.service;

import org.osgl.logging.LogService;
import org.osgl.logging.LogServiceProvider;

/**
 * Created by luog on 15/02/14.
 */
public class Log4jServiceProvider implements LogServiceProvider {
    @Override
    public LogService getLogService(String name) {
        return new Log4jService(name);
    }
}
