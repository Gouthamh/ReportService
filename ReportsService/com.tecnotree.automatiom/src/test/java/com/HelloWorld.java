package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class HelloWorld {
	
	public static Logger logger = LogManager.getFormatterLogger(HelloWorld.class);

	public void testLoggerDebug() {
		logger.debug("Hello.. im in Debug method");
	}

	public void testLoggerInfo() {
		logger.info("Hello.. im in Info method");
	}

	public void testLoggerWarn() {
		logger.warn("Hello.. im in Warn method");
	}

	public void testLoggerError() {
		logger.error("Hello.. im in Error method");
	}

	public void testLoggerFatal() {
		logger.fatal("Hello.. im in Fatal method");
	}

	public static void main(String[] args) {
		HelloWorld example = new HelloWorld();
		example.testLoggerDebug();
		example.testLoggerInfo();
		example.testLoggerWarn();
		example.testLoggerError();
		example.testLoggerFatal();
	}

}