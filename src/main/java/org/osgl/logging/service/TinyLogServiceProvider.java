package org.osgl.logging.service;

import org.osgl.logging.LogService;
import org.osgl.logging.LogServiceProvider;

/**
 * Created by luog on 15/02/14.
 */
public class TinyLogServiceProvider implements LogServiceProvider {
    @Override
    public LogService getLogService(Class<?> clazz) {
        return new TinyLogService(clazz);
    }
}
