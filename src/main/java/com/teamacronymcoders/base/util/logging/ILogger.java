package com.teamacronymcoders.base.util.logging;

import org.apache.logging.log4j.Logger;

public interface ILogger {
    void warning(String message);

    void info(String message);

    void fatal(String message);

    void error(String message);

    void error(Throwable throwable);

    void devWarning(String message);

    void devInfo(String message);

    void devFatal(String message);

    void devError(String message);

    Logger getLogger();
}
