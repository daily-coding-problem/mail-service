package com.dcp.mail_service.v1.controllers;

import com.dcp.api_service.v1.entities.Problem;
import com.dcp.api_service.v1.entities.User;
import com.dcp.api_service.v1.services.APIService;
import com.dcp.api_service.v1.services.ProblemService;
import com.dcp.email_service.v1.services.EmailService;
import com.dcp.mail_service.v1.entities.Email;
import com.dcp.mail_service.v1.services.MailService;
import com.dcp.mail_service.v1.services.markdown.MarkdownConverter;
import com.dcp.mail_service.v1.services.markdown.MarkdownDetector;
import com.resend.core.exception.ResendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MailControllerImpl implements MailController {
	private final APIService apiService;
	private final ProblemService problemService;
	private final EmailService emailService;
	private final MailService mailService;

	@Autowired
	public MailControllerImpl(APIService apiService, ProblemService problemService, EmailService emailService, MailService mailService) {
		this.apiService = apiService;
		this.problemService = problemService;
		this.emailService = emailService;
		this.mailService = mailService;
	}

	@Override
	public String getEmailContentForProblem(@PathVariable String slug, boolean provideSolution) {
		Problem problem = apiService.getProblemBySlug(slug);

		return generateEmailContent(problem, provideSolution);
	}

	@Override
	public String getEmailContentForRandomProblem(boolean provideSolution) {
		Problem problem = apiService.getRandomProblem();

		return generateEmailContent(problem, provideSolution);
	}

	@Override
	public String getEmailContentForProblemForUser(String slug, User user) {
		Problem problem = apiService.getProblemBySlug(slug);

		return generateEmailContent(problem, user.isPremium(), user.getUnsubscribeToken());
	}

	private String generateEmailContent(Problem problem, boolean provideSolution) {
		return generateEmailContent(problem, provideSolution, null);
	}

	private String generateEmailContent(Problem problem, boolean provideSolution, String unsubscribeToken) {
		if (problem == null) {
			return "Problem not found";
		}

		if (provideSolution) {
			if (!problem.getSolution().isEmpty()) {
				return emailService.generate(problem, unsubscribeToken);
			}

			String solution = problemService.getSolution(problem);

			if (solution == null) {
				return "Failed to generate solution.";
			}

			if (MarkdownDetector.isMarkdown(solution)) {
				solution = MarkdownConverter.convertMarkdown(solution, MarkdownConverter.OutputType.HTML);
				problem.setSolution(solution);

				// Update the problem with the solution
				// This way, we don't have to generate
				// the solution again.
				problemService.updateProblem(problem);
			}
		}

		return emailService.generate(problem, unsubscribeToken);
	}

	@Override
	public void sendEmail(Email email) throws ResendException {
		mailService.sendEmail(email);
	}
}
