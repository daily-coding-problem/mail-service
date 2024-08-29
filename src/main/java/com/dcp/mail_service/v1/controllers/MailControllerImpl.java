package com.dcp.mail_service.v1.controllers;

import com.dcp.api_service.v1.entities.Problem;
import com.dcp.api_service.v1.services.APIService;
import com.dcp.mail_service.v1.entities.Email;
import com.dcp.mail_service.v1.services.MailService;
import com.dcp.mail_service.v1.services.ThymeleafTemplateService;
import com.resend.core.exception.ResendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MailControllerImpl implements MailController {
	private final MailService mailService;
	private final APIService apiService;
	private final ThymeleafTemplateService templateService;

	@Autowired
	public MailControllerImpl(MailService mailService, APIService apiService, ThymeleafTemplateService templateService) {
		this.mailService = mailService;
		this.apiService = apiService;
		this.templateService = templateService;
	}

	@Override
	public String getEmailContentForProblem(@PathVariable String slug, boolean provideSolution) {
		Problem problem = apiService.getProblemBySlug(slug);

		if (problem == null) {
			return "Problem not found";
		}

		if (provideSolution) {
			return templateService.generateSolutionEmailContent(problem);
		}

		return templateService.generateProblemEmailContent(problem);
	}

	@Override
	public String getEmailContentForProblem(boolean provideSolution) {
		Problem problem = apiService.getRandomProblem();

		if (problem == null) {
			return "Problem not found";
		}

		if (provideSolution) {
			return templateService.generateSolutionEmailContent(problem);
		}

		return templateService.generateProblemEmailContent(problem);
	}

	@Override
	public void sendEmail(String slug, Email email) throws ResendException {
		Problem problem = apiService.getProblemBySlug(slug);

		if (problem == null) {
			return;
		}

		String html = templateService.generateProblemEmailContent(problem);
		email.setHtml(html);
		email.setSubject("Daily Coding Problem - " + problem.getTitle());

		mailService.sendEmail(email);
	}

	@Override
	public void sendEmail(Email email) throws ResendException {
		Problem problem = apiService.getRandomProblem();

		if (problem == null) {
			return;
		}

		String html = templateService.generateProblemEmailContent(problem);
		email.setHtml(html);
		email.setSubject("Daily Coding Problem - " + problem.getTitle());

		mailService.sendEmail(email);
	}
}
