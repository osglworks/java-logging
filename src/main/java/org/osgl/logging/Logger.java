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

public interface Logger extends LogService {

    public void setLevel(Level level);

    public void trace(String format, Object ... args);
    public void trace(Throwable t, String format, Object ... args);

    public void debug(String format, Object ... args);
    public void debug(Throwable t, String format, Object ... args);

    public void info(String format, Object ... args);
    public void info(Throwable t, String format, Object ... args);

    public void warn(String format, Object ... args);
    public void warn(Throwable t, String format, Object ... args);
    
    public void error(String format, Object ... args);
    public void error(Throwable t, String format, Object ... args);

    public void fatal(String format, Object ... args);
    public void fatal(Throwable t, String format, Object ... args);

    enum Level {
        FATAL,
        ERROR,
        WARN,
        INFO,
        DEBUG,
        TRACE;

        public boolean isTraceEnabled() {
            return this.ordinal() >= TRACE.ordinal();
        }

        public boolean isDebugEnabled() {
            return this.ordinal() > TRACE.ordinal();
        }

        public boolean isInfoEnabled() {
            return this.ordinal() > DEBUG.ordinal();
        }

        public boolean isWarnEnabled() {
            return this.ordinal() > INFO.ordinal();
        }

        public boolean isErrorEnabled() {
            return this.ordinal() > WARN.ordinal();
        }

        public boolean isFatalEnabled() {
            return true;
        }
    }
}
