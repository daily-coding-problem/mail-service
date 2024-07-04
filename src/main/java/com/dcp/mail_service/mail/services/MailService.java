package com.dcp.mail_service.mail.services;

import com.dcp.mail_service.mail.models.Email;
import org.springframework.mail.MailSendException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public interface MailService {
	@Retryable(
		maxAttemptsExpression = "${spring.retry.max-attempts}",
		backoff = @Backoff(delayExpression = "${spring.retry.max-delay}"))
	@Async
	void sendEmail(Email email) throws MailSendException;

	@Recover
	void recover(MailSendException e, Email email);
}
