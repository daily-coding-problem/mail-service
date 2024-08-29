package com.dcp.mail_service.v1.services;

import com.dcp.config.properties.Properties;
import com.dcp.config.properties.enums.EnvironmentName;
import com.dcp.mail_service.v1.entities.Email;
import com.resend.Resend;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MailServiceImpl implements MailService {
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	private final Properties properties;

	@Autowired
	public MailServiceImpl(Properties properties) {
		this.properties = properties;
	}

	@Override
	public void sendEmail(Email email) {
		Resend resend = new Resend(properties.getResend().getApiKey());

		// When using 'Resend', we can only send emails from the 'from' address that is verified in the Resend account.
		// Moreover, we can only send emails to the 'to' address that is verified in the Resend account.
		String from = determineFromAddress(email);
		String to = determineToAddress(email);

		CreateEmailOptions params = buildEmailOptions(from, to, email);

		try {
			CreateEmailResponse data = resend.emails().send(params);
			logger.info("Email sent: {}", data);
		} catch (Exception e) {
			logger.error("Failed to send email: {}", email, e);
		}
	}

	private String determineFromAddress(Email email) {
		return properties.getEnvironment().getName().equals(EnvironmentName.DEVELOPMENT.getValue())
			? properties.getMail().getFrom()
			: email.getFrom();
	}

	private String determineToAddress(Email email) {
		return properties.getEnvironment().getName().equals(EnvironmentName.DEVELOPMENT.getValue())
			? properties.getMail().getTo()
			: email.getTo();
	}

	private CreateEmailOptions buildEmailOptions(String from, String to, Email email) {
		return CreateEmailOptions.builder()
			.from(from)
			.to(to)
			.subject(email.getSubject())
			.html(email.getHtml())
			.build();
	}


	@Override
	public void recover(Exception e, Email email) {
		logger.error("Failed to send email: {}", email, e);
	}
}
