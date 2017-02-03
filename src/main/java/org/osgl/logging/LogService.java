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

import java.io.Serializable;

public interface LogService extends Serializable {
    void setLevel(Logger.Level level);
    boolean isTraceEnabled();
    void trace(String msg);
    void trace(Throwable t, String msg);

    boolean isDebugEnabled();
    void debug(String msg);
    void debug(Throwable t, String msg);

    boolean isInfoEnabled();
    void info(String msg);
    void info(Throwable t, String msg);

    boolean isWarnEnabled();
    void warn(String msg);
    void warn(Throwable t, String msg);

    boolean isErrorEnabled();
    void error(String msg);
    void error(Throwable t, String msg);

    void fatal(String msg);
    void fatal(Throwable t, String msg);
}
