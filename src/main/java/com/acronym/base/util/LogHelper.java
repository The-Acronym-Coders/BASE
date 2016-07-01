package com.acronym.base.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHelper {

	private final String name;
	private final Logger logger;

	/**
	 * LogHelper to make logging easier
	 *
	 * @param name name of current mod using it.
	 */
	public LogHelper(String name) {
		this.name = name;
		logger = LogManager.getLogger(name);
	}

	/**
	 * Log with level
	 * @param logLevel Level of logging
	 * @param message
	 */
	public void log(Level logLevel, String message) {
		switch (logLevel) {
			case DEBUG:
				if(Platform.isDevEnv())
					logger.log(logLevel,message);
				break;
			default:
				logger.log(logLevel,message);
				break;
		}
	}

	public void all(String message) {
		log(Level.ALL, message);
	}

	public void debug(String message) {
		log(Level.DEBUG, message);
	}

	public void trace(String message) {
		log(Level.TRACE, message);
	}

	public void fatal(String message) {
		log(Level.FATAL, message);
	}

	public void error(String message) {
		log(Level.ERROR, message);
	}

	public void warn(String message) {
		log(Level.WARN, message);
	}

	public void info(String message) {
		log(Level.INFO, message);
	}

	public void off(String message) {
		log(Level.OFF, message);
	}
}
