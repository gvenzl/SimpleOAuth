/*
 * Since: June 2022
 * Author: gvenzl
 * Name: Logger.java
 * Description:
 *
 * Copyright 2022 Gerald Venzl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.gvenzl.oauth.log;

public class Logger {

    private static LogLevel logLevel = LogLevel.OFF;

    /**
     * Sets the log level, default OFF.
     * @param logLevel the log level to set.
     */
    public void setLogLevel(LogLevel logLevel) {
        Logger.logLevel = logLevel;
    }

    /**
     * Writes a Debug log message.
     * @param message the message to log.
     */
    public static void debug(String message) {
        if (logLevel == LogLevel.DEBUG || logLevel == LogLevel.INFO ) {
            log(LogLevel.DEBUG, message);
        }
    }

    /**
     * Writes a INFO log message.
     * @param message the message to log.
     */
    public static void info(String message) {
        if (logLevel == LogLevel.DEBUG) {
            log(LogLevel.INFO, message);
        }
    }

    /**
     * Writes a message to the message.
     * @param level the message level.
     * @param message the message to message.
     */
    private static void log(LogLevel level,  String message) {
        System.out.printf("%s: %s%n", level, message);
    }

}
