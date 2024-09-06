package com.dcp.api_service.v1.services;

import com.dcp.api_service.v1.entities.Problem;
import com.dcp.config.properties.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {
	private final SolutionService solutionService;
	private final RestTemplate restTemplate;
	private final Properties properties;

	@Autowired
	public ProblemServiceImpl(SolutionService solutionService, RestTemplate restTemplate, Properties properties) {
		this.solutionService = solutionService;
		this.restTemplate = restTemplate;
		this.properties = properties;
	}

	@Override
	public List<Problem> getAllProblems() {
		String url = properties.getServices().getApi().getUrl() + "/api/v1/problems";
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
		Problem[] problems = restTemplate.getForObject(uriBuilder.toUriString(), Problem[].class);

		if (problems == null) {
			return Collections.emptyList();
		}

		return Arrays.asList(problems);
	}

	@Override
	public Problem getProblemBySlug(String slug) {
		String url = properties.getServices().getApi().getUrl() + "/api/v1/problems/" + slug;
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);

		return restTemplate.getForObject(uriBuilder.toUriString(), Problem.class);
	}

	@Override
	public Problem createProblem(Problem problem) {
		String url = properties.getServices().getApi().getUrl() + "/api/v1/problems";

		return restTemplate.postForObject(url, problem, Problem.class);
	}

	@Override
	public Problem getRandomProblem() {
		String url = properties.getServices().getApi().getUrl() + "/api/v1/problems/random";
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);

		return restTemplate.getForObject(uriBuilder.toUriString(), Problem.class);
	}

	@Override
	public void updateProblem(Problem problem) {
		String url = properties.getServices().getApi().getUrl() + "/api/v1/problems/" + problem.getSlug();

		restTemplate.put(url, problem);
	}

	@Override
	public String getSolution(Problem problem) {
		String prompt = generatePrompt(problem);

		return solutionService.getSolutionFromChatGPT(prompt);
	}

	private String generatePrompt(Problem problem) {
		StringBuilder prompt = new StringBuilder();
		prompt.append("Problem Title: ").append(problem.getTitle()).append("\n");
		prompt.append("Problem Content: ").append(problem.getContent()).append("\n");
		prompt.append("Difficulty: ").append(problem.getDifficulty()).append("\n");

		if (problem.getTopics() != null && !problem.getTopics().isEmpty()) {
			prompt.append("Topics: ").append(String.join(", ", problem.getTopics())).append("\n");
		}

		if (problem.getCompanies() != null && !problem.getCompanies().isEmpty()) {
			prompt.append("Companies: ").append(String.join(", ", problem.getCompanies())).append("\n");
		}

		if (problem.getHints() != null && !problem.getHints().isEmpty()) {
			prompt.append("Hints: ").append(String.join(", ", problem.getHints())).append("\n");
		}

		prompt.append("\nGenerate a solution in Markdown for the above problem.");
		prompt.append("\nOnly include the solution and example it in detail.");

		return prompt.toString();
	}
}
