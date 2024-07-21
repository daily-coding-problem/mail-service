package com.dcp.api_service.v1.services;

import com.dcp.api_service.v1.entities.User;
import com.dcp.config.properties.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	private final RestTemplate restTemplate;
	private final Properties properties;

	@Autowired
	public UserServiceImpl(RestTemplate restTemplate, Properties properties) {
		this.restTemplate = restTemplate;
		this.properties = properties;
	}

	@Override
	public List<User> getAllUsers() {
		String url = properties.getServices().getApi().getUrl() + "/api/v1/users";
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
		User[] users = restTemplate.getForObject(uriBuilder.toUriString(), User[].class);

		if (users == null) {
			return Collections.emptyList();
		}

		return Arrays.asList(users);
	}

	@Override
	public User getUserByEmail(String email) {
		String url = properties.getServices().getApi().getUrl() + "/api/v1/users/" + email;
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);

		return restTemplate.getForObject(uriBuilder.toUriString(), User.class);
	}

	@Override
	public List<User> getAllPremiumUsers() {
		String url = properties.getServices().getApi().getUrl() + "/api/v1/users/premium";
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
		User[] users = restTemplate.getForObject(uriBuilder.toUriString(), User[].class);

		if (users == null) {
			return Collections.emptyList();
		}

		return Arrays.asList(users);
	}
}
