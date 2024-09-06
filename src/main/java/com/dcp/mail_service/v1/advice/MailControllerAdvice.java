package com.dcp.mail_service.v1.advice;

import com.resend.core.exception.ResendException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MailControllerAdvice {
	private final Logger logger = LoggerFactory.getLogger(MailControllerAdvice.class);

	@ExceptionHandler(ResendException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public ResponseEntity<String> handleResendException(ResendException ex) {
		logger.error("Failed to send email: {}", ex.getMessage(), ex);
		return new ResponseEntity<>("Failed to send email: " + ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<?> handleGenericException(Exception ex) {
		logger.error("An error occurred: {}", ex.getMessage(), ex);
		return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
