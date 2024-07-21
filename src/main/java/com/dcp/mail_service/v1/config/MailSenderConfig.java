package com.dcp.mail_service.v1.config;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.security.SecureRandom;
import java.util.List;

//@Component
public class MailSenderConfig {

	private static final Logger logger = LoggerFactory.getLogger(MailSenderConfig.class);

	@Value("${SMTP_RELAYS}")
	private String[] smtpRelays;

	private final List<JavaMailSenderImpl> senderList;

	public MailSenderConfig(List<JavaMailSenderImpl> senderList) {
		this.senderList = senderList;
	}

	@PostConstruct
	public void buildMailSender() {
		for (String smtpRelay : smtpRelays) {
			try {
				logger.trace("Adding SMTP relay: {}", smtpRelay);
				JavaMailSenderImpl sender = new JavaMailSenderImpl();
				sender.setHost(smtpRelay);
				sender.setPort(25);
				sender.setProtocol("smtp");
				sender.setDefaultEncoding("UTF-8");
				sender.testConnection();
				senderList.add(sender);
				logger.trace("Added SMTP relay: {}", smtpRelay);
			} catch (MessagingException e) {
				logger.error("Failed to connect to SMTP relay: {}", smtpRelay);
			}
			if (senderList.isEmpty()) {
				logger.error("No SMTP relay is available. Please check the SMTP_RELAYS property.");
				throw new RuntimeException(
					"No SMTP relay is available. Please check the SMTP_RELAYS property.");
			}
		}
	}

	public JavaMailSenderImpl getSender() {
		if (senderList.isEmpty()) {
			buildMailSender();
		}

		return senderList.get(new SecureRandom().nextInt(senderList.size()));
	}
}
