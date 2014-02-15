package org.osgl.logging.service;

import org.osgl.logging.LogService;
import org.osgl.logging.LogServiceProvider;

/**
 * Created by luog on 15/02/14.
 */
public class Slf4jServiceProvider implements LogServiceProvider {
    @Override
    public LogService getLogService(Class<?> clazz) {
        return new Slf4jService(clazz);
    }
}
