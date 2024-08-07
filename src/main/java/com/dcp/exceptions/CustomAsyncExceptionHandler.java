package com.dcp.exceptions;

import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomAsyncExceptionHandler.class);

	@Override
	public void handleUncaughtException(Throwable ex, Method method, Object... params) {
		logger.error(
			"Unexpected asynchronous exception at : {}.{}",
			method.getDeclaringClass().getName(),
			method.getName());
		for (Object param : params) {
			logger.error("Parameter value - {}", param);
		}
		logger.error("Exception message - {}", ex.getMessage());
	}
}
