package com.dcp.mail_service.v1.services;

import com.dcp.ap_service.v1.entities.Problem;
import com.dcp.ap_service.v1.services.ProblemService;
import com.dcp.mail_service.v1.services.markdown.MarkdownConverter;
import com.dcp.mail_service.v1.services.markdown.MarkdownDetector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class ThymeleafTemplateService {

	private final TemplateEngine templateEngine;
	private final ProblemService problemService;

	@Autowired
	public ThymeleafTemplateService(TemplateEngine templateEngine, ProblemService problemService) {
		this.templateEngine = templateEngine;
		this.problemService = problemService;
	}

	public String generateProblemEmailContent(Problem problem) {
		Context context = new Context();
		context.setVariable("problem", problem);

		return templateEngine.process("template", context);
	}

	public String generateSolutionEmailContent(Problem problem) {
		Context context = new Context();
		context.setVariable("problem", problem);

		String solution = problemService.getSolution(problem);

		if (solution == null) {
			return "Failed to generate solution. Please try again later.";
		}

		if (MarkdownDetector.isMarkdown(solution)) {
			solution = MarkdownConverter.convertMarkdown(solution, MarkdownConverter.OutputType.HTML);
			context.setVariable("solution", solution);
		}

		return templateEngine.process("template", context);
	}
}
