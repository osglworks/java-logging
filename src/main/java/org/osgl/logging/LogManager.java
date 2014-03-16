/*
 * Copyright (C) 2013 The OSGL Logging project
 * Gelin Luo <greenlaw110(at)gmail.com>
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package org.osgl.logging;

import org.osgl._;
import org.osgl.logging.service.JDKLogService;
import org.osgl.util.S;

/**
 * Created by luog on 14/02/14.
 */
public class LogManager {

    // inheritance open to package level
    LogManager() {
    }

    private static class Proxy implements Logger {
        private Class<?> c_;
        private String nm_;
        private volatile LogService l_;

        Proxy(String name) {
            nm_ = name;
        }

        LogService impl() {
            if (null == l_) {
                synchronized (this) {
                    if (null == l_) {
                        l_ = fact.getLogService(nm_);
                    }
                }
            }
            return l_;
        }

        private abstract class Level extends _.Visitor<String> {
            abstract public void visit(Throwable t, String msg);

            abstract public boolean isEnabled();
        }

        private Level trace = new Level() {
            @Override
            public boolean isEnabled() {
                return isTraceEnabled();
            }

            @Override
            public void visit(String s) throws _.Break {
                impl().trace(s);
            }

            @Override
            public void visit(Throwable t, String msg) {
                impl().trace(t, msg);
            }
        };
        private Level debug = new Level() {
            @Override
            public boolean isEnabled() {
                return isDebugEnabled();
            }

            @Override
            public void visit(String s) throws _.Break {
                impl().debug(s);
            }

            @Override
            public void visit(Throwable t, String msg) {
                impl().debug(t, msg);
            }
        };
        private Level info = new Level() {
            @Override
            public boolean isEnabled() {
                return isInfoEnabled();
            }

            @Override
            public void visit(String s) throws _.Break {
                impl().info(s);
            }

            @Override
            public void visit(Throwable t, String msg) {
                impl().info(t, msg);
            }
        };
        private Level warn = new Level() {
            @Override
            public boolean isEnabled() {
                return isWarnEnabled();
            }

            @Override
            public void visit(String s) throws _.Break {
                impl().warn(s);
            }

            @Override
            public void visit(Throwable t, String msg) {
                impl().warn(t, msg);
            }
        };
        private Level error = new Level() {
            @Override
            public boolean isEnabled() {
                return isErrorEnabled();
            }

            @Override
            public void visit(String s) throws _.Break {
                impl().error(s);
            }

            @Override
            public void visit(Throwable t, String msg) {
                impl().error(t, msg);
            }
        };
        private Level fatal = new Level() {
            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public void visit(String s) throws _.Break {
                impl().fatal(s);
            }

            @Override
            public void visit(Throwable t, String msg) {
                impl().fatal(t, msg);
            }
        };

        @Override
        public boolean isTraceEnabled() {
            return impl().isTraceEnabled();
        }

        private void log(Level level, String msg) {
            if (level.isEnabled()) level.visit(msg);
        }

        private void log(Level level, String format, Object... args) {
            if (level.isEnabled()) {
                try {
                    format = S.fmt(format, args);
                } catch (Exception e) {
                    // ignore
                }
                level.visit(format);
            }
        }

        private void log(Level level, Throwable t, String msg) {
            if (level.isEnabled()) level.visit(t, msg);
        }

        private void log(Level level, Throwable t, String format, Object... args) {
            if (level.isEnabled()) {
                try {
                    format = S.fmt(format, args);
                } catch (Exception e) {
                    // ignore
                }
                level.visit(t, format);
            }
        }

        @Override
        public void trace(String msg) {
            log(trace, msg);
        }

        @Override
        public void trace(String format, Object... args) {
            log(trace, format, args);
        }

        @Override
        public void trace(Throwable t, String msg) {
            log(trace, t, msg);
        }

        @Override
        public void trace(Throwable t, String format, Object... args) {
            log(trace, t, format, args);
        }

        @Override
        public boolean isDebugEnabled() {
            return impl().isDebugEnabled();
        }

        @Override
        public void debug(String msg) {
            log(debug, msg);
        }

        @Override
        public void debug(String format, Object... args) {
            log(debug, format, args);
        }

        @Override
        public void debug(Throwable t, String msg) {
            log(debug, t, msg);
        }

        @Override
        public void debug(Throwable t, String format, Object... args) {
            log(debug, t, format, args);
        }

        @Override
        public boolean isInfoEnabled() {
            return impl().isInfoEnabled();
        }

        @Override
        public void info(String msg) {
            log(info, msg);
        }

        @Override
        public void info(String format, Object... args) {
            log(info, format, args);
        }

        @Override
        public void info(Throwable t, String msg) {
            log(info, t, msg);
        }

        @Override
        public void info(Throwable t, String format, Object... args) {
            log(info, t, format, args);
        }

        @Override
        public boolean isWarnEnabled() {
            return impl().isWarnEnabled();
        }

        @Override
        public void warn(String msg) {
            log(warn, msg);
        }

        @Override
        public void warn(String format, Object... args) {
            log(warn, format, args);
        }

        @Override
        public void warn(Throwable t, String msg) {
            log(warn, t, msg);
        }

        @Override
        public void warn(Throwable t, String format, Object... args) {
            log(warn, t, format, args);
        }

        @Override
        public boolean isErrorEnabled() {
            return impl().isErrorEnabled();
        }

        @Override
        public void error(String msg) {
            log(error, msg);
        }

        @Override
        public void error(String format, Object... args) {
            log(error, format, args);
        }

        @Override
        public void error(Throwable t, String msg) {
            log(error, t, msg);
        }

        @Override
        public void error(Throwable t, String format, Object... args) {
            log(error, t, format, args);
        }

        @Override
        public void fatal(String msg) {
            log(fatal, msg);
        }

        @Override
        public void fatal(String format, Object... args) {
            log(fatal, format, args);
        }

        @Override
        public void fatal(Throwable t, String msg) {
            log(fatal, t, msg);
        }

        @Override
        public void fatal(Throwable t, String format, Object... args) {
            log(fatal, t, format, args);
        }

    }

    private static LogServiceProvider userFact = null;

    static {
        final String FQCN = LogManager.class.getName();
        try {
            userFact = _.newInstance("org.osgl.logging.service.Log4jServiceProvider");
            userFact.getLogService(FQCN);
        } catch (Throwable e) {
            userFact = null;
        }
        if (null == userFact) {
            try {
                userFact = _.newInstance("org.osgl.logging.service.TinyLogServiceProvider");
                userFact.getLogService(FQCN);
            } catch (Throwable e) {
                userFact = null;
            }
        }
        if (null == userFact) {
            try {
                userFact = _.newInstance("org.osgl.logging.service.Slf4jServiceProvider");
                userFact.getLogService(FQCN);
            } catch (Throwable e) {
                userFact = null;
            }
        }
        if (null == userFact) {
            try {
                userFact = _.newInstance("org.osgl.logging.service.CommonsLoggingServiceProvider");
                userFact.getLogService(FQCN);
            } catch (Throwable e) {
                userFact = null;
            }
        }
    }

    private static final LogServiceProvider fact = new LogServiceProvider() {
        private LogServiceProvider defFact = new JDKLogService.Factory();

        @Override
        public LogService getLogService(String name) {
            return null == userFact ? defFact.getLogService(name) : userFact.getLogService(name);
        }
    };

    /**
     * Get a Logger instance by class
     *
     * @param clazz the class to get the logger instance
     * @return the logger instance
     */
    public static Logger get(Class<?> clazz) {
        return new Proxy(clazz.getName());
    }

    public static Logger get(String name) {
        return new Proxy(name);
    }

    private static volatile Logger def = null;

    private static Logger def() {
        if (null == def) {
            synchronized (LogManager.class) {
                if (null == def) def = get(Object.class);
            }
        }
        return def;
    }

    public static void registerLogServiceProvider(LogServiceProvider fact) {
        reset();
        userFact = fact;
    }

    public static void reset() {
        userFact = null;
        def = null;
    }

    public static boolean isTraceEnabled() {
        return def().isTraceEnabled();
    }

    public static void trace(String msg) {
        def().trace(msg);
    }

    public static void trace(String format, Object... args) {
        def().trace(format, args);
    }

    public static void trace(Throwable t, String msg) {
        def().trace(t, msg);
    }

    public static void trace(Throwable t, String format, Object... args) {
        def().trace(t, format, args);
    }

    public static boolean isDebugEnabled() {
        return def().isDebugEnabled();
    }

    public static void debug(String msg) {
        def().debug(msg);
    }

    public static void debug(String format, Object... args) {
        def().debug(format, args);
    }

    public static void debug(Throwable t, String msg) {
        def().debug(t, msg);
    }

    public static void debug(Throwable t, String format, Object... args) {
        def().debug(t, format, args);
    }

    public static boolean isInfoEnabled() {
        return def().isInfoEnabled();
    }

    public static void info(String msg) {
        def().info(msg);
    }

    public static void info(String format, Object... args) {
        def().info(format, args);
    }

    public static void info(Throwable t, String msg) {
        def().info(t, msg);
    }

    public static void info(Throwable t, String format, Object... args) {
        def().info(t, format, args);
    }

    public static boolean isWarnEnabled() {
        return def().isWarnEnabled();
    }

    public static void warn(String msg) {
        def().warn(msg);
    }

    public static void warn(String format, Object... args) {
        def().warn(format, args);
    }

    public static void warn(Throwable t, String msg) {
        def().warn(t, msg);
    }

    public static void warn(Throwable t, String format, Object... args) {
        def().warn(t, format, args);
    }

    public static boolean isErrorEnabled() {
        return def().isErrorEnabled();
    }

    public static void error(String msg) {
        def().error(msg);
    }

    public static void error(String format, Object... args) {
        def().error(format, args);
    }

    public static void error(Throwable t, String msg) {
        def().error(t, msg);
    }

    public static void error(Throwable t, String format, Object... args) {
        def().error(t, format, args);
    }

    public static void fatal(String msg) {
        def().fatal(msg);
    }

    public static void fatal(String format, Object... args) {
        def().fatal(format, args);
    }

    public static void fatal(Throwable t, String msg) {
        def().fatal(t, msg);
    }

    public static void fatal(Throwable t, String format, Object... args) {
        def().fatal(t, format, args);
    }
}
