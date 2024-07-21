package com.dcp.mail_service.v1.controllers;

import com.dcp.ap_service.v1.entities.Problem;
import com.dcp.ap_service.v1.services.APIService;
import com.dcp.mail_service.v1.services.ThymeleafTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MailControllerImpl implements MailController {
	private final APIService apiService;
	private final ThymeleafTemplateService templateService;

	@Autowired
	public MailControllerImpl(APIService apiService, ThymeleafTemplateService templateService) {
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
}
