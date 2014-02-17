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
    LogManager() {}

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

        private void log(Level level, String format, Object arg1) {
            if (level.isEnabled()) {
                try {
                    format = S.fmt(format, arg1);
                } catch (Exception e) {
                    // ignore
                }
                level.visit(format);
            }
        }

        private void log(Level level, String format, Object arg1, Object arg2) {
            if (level.isEnabled()) {
                try {
                    format = S.fmt(format, arg1, arg2);
                } catch (Exception e) {
                    // ignore
                }
                level.visit(format);
            }
        }

        private void log(Level level, String format, Object arg1, Object arg2, Object arg3) {
            if (level.isEnabled()) {
                try {
                    format = S.fmt(format, arg1, arg2, arg3);
                } catch (Exception e) {
                    // ignore
                }
                level.visit(format);
            }
        }

        private void log(Level level, String format, Object arg1, Object arg2, Object arg3, Object ... args) {
            if (level.isEnabled()) {
                try {
                    format = S.fmt(format, arg1, arg2, arg3, args);
                } catch (Exception e) {
                    // ignore
                }
                level.visit(format);
            }
        }

        private void log(Level level, Throwable t, String msg) {
            if (level.isEnabled()) level.visit(t, msg);
        }

        private void log(Level level, Throwable t, String format, Object arg1) {
            if (level.isEnabled()) {
                try {
                    format = S.fmt(format, arg1);
                } catch (Exception e) {
                    // ignore
                }
                level.visit(t, format);
            }
        }

        private void log(Level level, Throwable t, String format, Object arg1, Object arg2) {
            if (level.isEnabled()) {
                try {
                    format = S.fmt(format, arg1, arg2);
                } catch (Exception e) {
                    // ignore
                }
                level.visit(t, format);
            }
        }

        private void log(Level level, Throwable t, String format, Object arg1, Object arg2, Object arg3) {
            if (level.isEnabled()) {
                try {
                    format = S.fmt(format, arg1, arg2, arg3);
                } catch (Exception e) {
                    // ignore
                }
                level.visit(t, format);
            }
        }

        private void log(Level level, Throwable t, String format, Object arg1, Object arg2, Object arg3, Object... args) {
            if (level.isEnabled()) {
                try {
                    format = S.fmt(format, arg1, arg2, arg3, args);
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
        public void trace(String format, Object arg1) {
            log(trace, format, arg1);
        }

        @Override
        public void trace(String format, Object arg1, Object arg2) {
            log(trace, format, arg1, arg2);
        }

        @Override
        public void trace(String format, Object arg1, Object arg2, Object arg3) {
            log(trace, format, arg1, arg2, arg3);
        }

        @Override
        public void trace(String format, Object arg1, Object arg2, Object arg3, Object... args) {
            log(trace, format, arg1, arg2, arg3, args);
        }

        @Override
        public void trace(Throwable t, String msg) {
            log(trace, t, msg);
        }

        @Override
        public void trace(Throwable t, String format, Object arg1) {
            log(trace, t, format, arg1);
        }

        @Override
        public void trace(Throwable t, String format, Object arg1, Object arg2) {
            log(trace, t, format, arg1, arg2);
        }

        @Override
        public void trace(Throwable t, String format, Object arg1, Object arg2, Object arg3) {
            log(trace, t, format, arg1, arg2, arg3);
        }

        @Override
        public void trace(Throwable t, String format, Object arg1, Object arg2, Object arg3, Object... args) {
            log(trace, t, format, arg1, arg2, arg3, args);
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
        public void debug(String format, Object arg1) {
            log(debug, format, arg1);
        }

        @Override
        public void debug(String format, Object arg1, Object arg2) {
            log(debug, format, arg1, arg2);
        }

        @Override
        public void debug(String format, Object arg1, Object arg2, Object arg3) {
            log(debug, format, arg1, arg2, arg3);
        }

        @Override
        public void debug(String format, Object arg1, Object arg2, Object arg3, Object... args) {
            log(debug, format, arg1, arg2, arg3, args);
        }

        @Override
        public void debug(Throwable t, String msg) {
            log(debug, t, msg);
        }

        @Override
        public void debug(Throwable t, String format, Object arg1) {
            log(debug, t, format, arg1);
        }

        @Override
        public void debug(Throwable t, String format, Object arg1, Object arg2) {
            log(debug, t, format, arg1, arg2);
        }

        @Override
        public void debug(Throwable t, String format, Object arg1, Object arg2, Object arg3) {
            log(debug, t, format, arg1, arg2, arg3);
        }

        @Override
        public void debug(Throwable t, String format, Object arg1, Object arg2, Object arg3, Object... args) {
            log(debug, t, format, arg1, arg2, arg3, args);
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
        public void info(String format, Object arg1) {
            log(info, format, arg1);
        }

        @Override
        public void info(String format, Object arg1, Object arg2) {
            log(info, format, arg1, arg2);
        }

        @Override
        public void info(String format, Object arg1, Object arg2, Object arg3) {
            log(info, format, arg1, arg2, arg3);
        }

        @Override
        public void info(String format, Object arg1, Object arg2, Object arg3, Object... args) {
            log(info, format, arg1, arg2, arg3, args);
        }

        @Override
        public void info(Throwable t, String msg) {
            log(info, t, msg);
        }

        @Override
        public void info(Throwable t, String format, Object arg1) {
            log(info, t, format, arg1);
        }

        @Override
        public void info(Throwable t, String format, Object arg1, Object arg2) {
            log(info, t, format, arg1, arg2);
        }

        @Override
        public void info(Throwable t, String format, Object arg1, Object arg2, Object arg3) {
            log(info, t, format, arg1, arg2, arg3);
        }

        @Override
        public void info(Throwable t, String format, Object arg1, Object arg2, Object arg3, Object... args) {
            log(info, t, format, arg1, arg2, arg3, args);
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
        public void warn(String format, Object arg1) {
            log(warn, format, arg1);
        }

        @Override
        public void warn(String format, Object arg1, Object arg2) {
            log(warn, format, arg1, arg2);
        }

        @Override
        public void warn(String format, Object arg1, Object arg2, Object arg3) {
            log(warn, format, arg1, arg2, arg3);
        }

        @Override
        public void warn(String format, Object arg1, Object arg2, Object arg3, Object... args) {
            log(warn, format, arg1, arg2, arg3, args);
        }

        @Override
        public void warn(Throwable t, String msg) {
            log(warn, t, msg);
        }

        @Override
        public void warn(Throwable t, String format, Object arg1) {
            log(warn, t, format, arg1);
        }

        @Override
        public void warn(Throwable t, String format, Object arg1, Object arg2) {
            log(warn, t, format, arg1, arg2);
        }

        @Override
        public void warn(Throwable t, String format, Object arg1, Object arg2, Object arg3) {
            log(warn, t, format, arg1, arg2, arg3);
        }

        @Override
        public void warn(Throwable t, String format, Object arg1, Object arg2, Object arg3, Object... args) {
            log(warn, t, format, arg1, arg2, arg3, args);
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
        public void error(String format, Object arg1) {
            log(error, format, arg1);
        }

        @Override
        public void error(String format, Object arg1, Object arg2) {
            log(error, format, arg1, arg2);
        }

        @Override
        public void error(String format, Object arg1, Object arg2, Object arg3) {
            log(error, format, arg1, arg2, arg3);
        }

        @Override
        public void error(String format, Object arg1, Object arg2, Object arg3, Object... args) {
            log(error, format, arg1, arg2, arg3, args);
        }

        @Override
        public void error(Throwable t, String msg) {
            log(error, t, msg);
        }

        @Override
        public void error(Throwable t, String format, Object arg1) {
            log(error, t, format, arg1);
        }

        @Override
        public void error(Throwable t, String format, Object arg1, Object arg2) {
            log(error, t, format, arg1, arg2);
        }

        @Override
        public void error(Throwable t, String format, Object arg1, Object arg2, Object arg3) {
            log(error, t, format, arg1, arg2, arg3);
        }

        @Override
        public void error(Throwable t, String format, Object arg1, Object arg2, Object arg3, Object... args) {
            log(error, t, format, arg1, arg2, arg3, args);
        }

        @Override
        public void fatal(String msg) {
            log(fatal, msg);
        }

        @Override
        public void fatal(String format, Object arg1) {
            log(fatal, format, arg1);
        }

        @Override
        public void fatal(String format, Object arg1, Object arg2) {
            log(fatal, format, arg1, arg2);
        }

        @Override
        public void fatal(String format, Object arg1, Object arg2, Object arg3) {
            log(fatal, format, arg1, arg2, arg3);
        }

        @Override
        public void fatal(String format, Object arg1, Object arg2, Object arg3, Object... args) {
            log(fatal, format, arg1, arg2, arg3, args);
        }

        @Override
        public void fatal(Throwable t, String msg) {
            log(fatal, t, msg);
        }

        @Override
        public void fatal(Throwable t, String format, Object arg1) {
            log(fatal, t, format, arg1);
        }

        @Override
        public void fatal(Throwable t, String format, Object arg1, Object arg2) {
            log(fatal, t, format, arg1, arg2);
        }

        @Override
        public void fatal(Throwable t, String format, Object arg1, Object arg2, Object arg3) {
            log(fatal, t, format, arg1, arg2, arg3);
        }

        @Override
        public void fatal(Throwable t, String format, Object arg1, Object arg2, Object arg3, Object... args) {
            log(fatal, t, format, arg1, arg2, arg3, args);
        }

    }

    private static LogServiceProvider userFact = null; static {
        final String FQCN = LogManager.class.getName();
        try {
            userFact = _.newInstance("org.osgl.logging.service.TinyLogServiceProvider");
            userFact.getLogService(FQCN);
        } catch (Throwable e) {
            e.printStackTrace();
            userFact = null;
        }
        if (null == userFact) {
            try {
                userFact = _.newInstance("org.osgl.logging.service.Slf4jServiceProvider");
                userFact.getLogService(FQCN);
            } catch (Throwable e) {
                e.printStackTrace();
                userFact = null;
            }
        }
        if (null == userFact) {
            try {
                userFact = _.newInstance("org.osgl.logging.service.CommonsLoggingServiceProvider");
                userFact.getLogService(FQCN);
            } catch (Throwable e) {
                e.printStackTrace();
                userFact = null;
            }
        }
        if (null == userFact) {
            try {
                userFact = _.newInstance("org.osgl.logging.service.Log4jServiceProvider");
                userFact.getLogService(FQCN);
            } catch (Throwable e) {
                e.printStackTrace();
                userFact = null;
            }
        }
        _.echo("user factory initialized to %s", userFact);
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

    public static void trace(String format, Object arg1) {
        def().trace(format, arg1);
    }

    public static void trace(String format, Object arg1, Object arg2) {
        def().trace(format, arg1, arg2);
    }

    public static void trace(String format, Object arg1, Object arg2, Object arg3) {
        def().trace(format, arg1, arg2, arg3);
    }

    public static void trace(String format, Object arg1, Object arg2, Object arg3, Object... args) {
        def().trace(format, arg1, arg2, arg3, args);
    }

    public static void trace(Throwable t, String msg) {
        def().trace(t, msg);
    }

    public static void trace(Throwable t, String format, Object arg1) {
        def().trace(t, format, arg1);
    }

    public static void trace(Throwable t, String format, Object arg1, Object arg2) {
        def().trace(t, format, arg1, arg2);
    }

    public static void trace(Throwable t, String format, Object arg1, Object arg2, Object arg3) {
        def().trace(t, format, arg1, arg2, arg3);
    }

    public static void trace(Throwable t, String format, Object arg1, Object arg2, Object arg3, Object... args) {
        def().trace(t, format, arg1, arg2, arg3, args);
    }

    public static boolean isDebugEnabled() {
        return def().isDebugEnabled();
    }

    public static void debug(String msg) {
        def().debug(msg);
    }

    public static void debug(String format, Object arg1) {
        def().debug(format, arg1);
    }

    public static void debug(String format, Object arg1, Object arg2) {
        def().debug(format, arg1, arg2);
    }

    public static void debug(String format, Object arg1, Object arg2, Object arg3) {
        def().debug(format, arg1, arg2, arg3);
    }

    public static void debug(String format, Object arg1, Object arg2, Object arg3, Object... args) {
        def().debug(format, arg1, arg2, arg3, args);
    }

    public static void debug(Throwable t, String msg) {
        def().debug(t, msg);
    }

    public static void debug(Throwable t, String format, Object arg1) {
        def().debug(t, format, arg1);
    }

    public static void debug(Throwable t, String format, Object arg1, Object arg2) {
        def().debug(t, format, arg1, arg2);
    }

    public static void debug(Throwable t, String format, Object arg1, Object arg2, Object arg3) {
        def().debug(t, format, arg1, arg2, arg3);
    }

    public static void debug(Throwable t, String format, Object arg1, Object arg2, Object arg3, Object... args) {
        def().debug(t, format, arg1, arg2, arg3, args);
    }

    public static boolean isInfoEnabled() {
        return def().isInfoEnabled();
    }

    public static void info(String msg) {
        def().info(msg);
    }

    public static void info(String format, Object arg1) {
        def().info(format, arg1);
    }

    public static void info(String format, Object arg1, Object arg2) {
        def().info(format, arg1, arg2);
    }

    public static void info(String format, Object arg1, Object arg2, Object arg3) {
        def().info(format, arg1, arg2, arg3);
    }

    public static void info(String format, Object arg1, Object arg2, Object arg3, Object... args) {
        def().info(format, arg1, arg2, arg3, args);
    }

    public static void info(Throwable t, String msg) {
        def().info(t, msg);
    }

    public static void info(Throwable t, String format, Object arg1) {
        def().info(t, format, arg1);
    }

    public static void info(Throwable t, String format, Object arg1, Object arg2) {
        def().info(t, format, arg1, arg2);
    }

    public static void info(Throwable t, String format, Object arg1, Object arg2, Object arg3) {
        def().info(t, format, arg1, arg2, arg3);
    }

    public static void info(Throwable t, String format, Object arg1, Object arg2, Object arg3, Object... args) {
        def().info(t, format, arg1, arg2, arg3, args);
    }

    public static boolean isWarnEnabled() {
        return def().isWarnEnabled();
    }

    public static void warn(String msg) {
        def().warn(msg);
    }

    public static void warn(String format, Object arg1) {
        def().warn(format, arg1);
    }

    public static void warn(String format, Object arg1, Object arg2) {
        def().warn(format, arg1, arg2);
    }

    public static void warn(String format, Object arg1, Object arg2, Object arg3) {
        def().warn(format, arg1, arg2, arg3);
    }

    public static void warn(String format, Object arg1, Object arg2, Object arg3, Object... args) {
        def().warn(format, arg1, arg2, arg3, args);
    }

    public static void warn(Throwable t, String msg) {
        def().warn(t, msg);
    }

    public static void warn(Throwable t, String format, Object arg1) {
        def().warn(t, format, arg1);
    }

    public static void warn(Throwable t, String format, Object arg1, Object arg2) {
        def().warn(t, format, arg1, arg2);
    }

    public static void warn(Throwable t, String format, Object arg1, Object arg2, Object arg3) {
        def().warn(t, format, arg1, arg2, arg3);
    }

    public static void warn(Throwable t, String format, Object arg1, Object arg2, Object arg3, Object... args) {
        def().warn(t, format, arg1, arg2, arg3, args);
    }

    public static boolean isErrorEnabled() {
        return def().isErrorEnabled();
    }

    public static void error(String msg) {
        def().error(msg);
    }

    public static void error(String format, Object arg1) {
        def().error(format, arg1);
    }

    public static void error(String format, Object arg1, Object arg2) {
        def().error(format, arg1, arg2);
    }

    public static void error(String format, Object arg1, Object arg2, Object arg3) {
        def().error(format, arg1, arg2, arg3);
    }

    public static void error(String format, Object arg1, Object arg2, Object arg3, Object... args) {
        def().error(format, arg1, arg2, arg3, args);
    }

    public static void error(Throwable t, String msg) {
        def().error(t, msg);
    }

    public static void error(Throwable t, String format, Object arg1) {
        def().error(t, format, arg1);
    }

    public static void error(Throwable t, String format, Object arg1, Object arg2) {
        def().error(t, format, arg1, arg2);
    }

    public static void error(Throwable t, String format, Object arg1, Object arg2, Object arg3) {
        def().error(t, format, arg1, arg2, arg3);
    }

    public static void error(Throwable t, String format, Object arg1, Object arg2, Object arg3, Object... args) {
        def().error(t, format, arg1, arg2, arg3, args);
    }

    public static void fatal(String msg) {
        def().fatal(msg);
    }

    public static void fatal(String format, Object arg1) {
        def().fatal(format, arg1);
    }

    public static void fatal(String format, Object arg1, Object arg2) {
        def().fatal(format, arg1, arg2);
    }

    public static void fatal(String format, Object arg1, Object arg2, Object arg3) {
        def().fatal(format, arg1, arg2, arg3);
    }

    public static void fatal(String format, Object arg1, Object arg2, Object arg3, Object... args) {
        def().fatal(format, arg1, arg2, arg3, args);
    }

    public static void fatal(Throwable t, String msg) {
        def().fatal(t, msg);
    }

    public static void fatal(Throwable t, String format, Object arg1) {
        def().fatal(t, format, arg1);
    }

    public static void fatal(Throwable t, String format, Object arg1, Object arg2) {
        def().fatal(t, format, arg1, arg2);
    }

    public static void fatal(Throwable t, String format, Object arg1, Object arg2, Object arg3) {
        def().fatal(t, format, arg1, arg2, arg3);
    }

    public static void fatal(Throwable t, String format, Object arg1, Object arg2, Object arg3, Object... args) {
        def().fatal(t, format, arg1, arg2, arg3, args);
    }
}
