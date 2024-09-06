package com.dcp.email_service.v1.services;

import com.dcp.api_service.v1.entities.Problem;
import com.dcp.config.properties.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EmailServiceImpl implements EmailService {
	private final RestTemplate restTemplate;
	private final Properties properties;

	@Autowired
	public EmailServiceImpl(RestTemplate restTemplate, Properties properties) {
		this.restTemplate = restTemplate;
		this.properties = properties;
	}

	@Override
	public String generate(Problem problem) {
		String url = properties.getServices().getEmail().getUrl() + "/api/v1/email/generate";
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);

		return restTemplate.postForObject(uriBuilder.toUriString(), problem, String.class);
	}
}
