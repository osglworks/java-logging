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

/**
 * Created by luog on 15/02/14.
 */
public interface LogService extends Serializable {
    public boolean isTraceEnabled();
    public void trace(String msg);
    public void trace(Throwable t, String msg);

    public boolean isDebugEnabled();
    public void debug(String msg);
    public void debug(Throwable t, String msg);

    public boolean isInfoEnabled();
    public void info(String msg);
    public void info(Throwable t, String msg);

    public boolean isWarnEnabled();
    public void warn(String msg);
    public void warn(Throwable t, String msg);

    public boolean isErrorEnabled();
    public void error(String msg);
    public void error(Throwable t, String msg);

    public void fatal(String msg);
    public void fatal(Throwable t, String msg);
}
