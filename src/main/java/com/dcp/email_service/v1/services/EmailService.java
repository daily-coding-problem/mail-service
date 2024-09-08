package com.dcp.email_service.v1.services;

import com.dcp.api_service.v1.entities.Problem;

public interface EmailService {
	String generate(Problem problem, String unsubscribeToken);
}
