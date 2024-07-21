package com.dcp.mail_service.v1.services;

import com.dcp.api_service.v1.services.APIService;
import com.dcp.config.properties.Properties;
import com.dcp.mail_service.v1.entities.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
	private final APIService apiService;
	private final Properties properties;

	@Autowired
	public MailServiceImpl(APIService apiService, Properties properties) {
		this.apiService = apiService;
		this.properties = properties;
	}

	@Override
	public void sendEmail(Email email) throws MailSendException {

	}

	@Override
	public void recover(MailSendException e, Email email) {

	}
}
