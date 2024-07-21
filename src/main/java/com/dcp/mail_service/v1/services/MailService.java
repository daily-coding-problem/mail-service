package com.dcp.mail_service.v1.services;

import com.dcp.mail_service.v1.entities.Email;
import org.springframework.mail.MailSendException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;

public interface MailService {
	@Retryable(
		maxAttemptsExpression = "${spring.retry.max-attempts}",
		backoff = @Backoff(delayExpression = "${spring.retry.max-delay}"))
	@Async
	void sendEmail(Email email) throws MailSendException;

	@Recover
	void recover(MailSendException e, Email email);
}
