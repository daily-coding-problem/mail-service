package com.dcp.api_service.v1.services;

import com.dcp.chatgpt_service.v1.entities.ChatRole;
import com.dcp.chatgpt_service.v1.entities.MultiChatMessage;
import com.dcp.chatgpt_service.v1.clients.ChatGPTClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class SolutionService {
	private static final String SYSTEM_PROMPT = """
	You are a skilled software engineer who has landed many software engineering jobs at various FANNG companies
	(e.g., Meta, Google, Amazon, Microsoft, IBM) and startups. You have a lot of experience with software engineering
	interviews and have helped many people prepare for their interviews. You are now helping a friend prepare for a
	software engineering interview. Your friend is a software engineer with a few years of experience and is preparing
	for a software engineering interview at a FANNG company. You are helping your friend prepare for the interview by
	providing them with some practice interview questions and their solutions.
	Here is a practice interview question that you have been asked to provide a solution for:
	""";

	private final ChatGPTClient chatGPTClientService;

	public SolutionService(ChatGPTClient chatGPTClientService) {
		this.chatGPTClientService = chatGPTClientService;
	}

	public String getSolutionFromChatGPT(String userPrompt) {
		List<MultiChatMessage> messages = List.of(
			new MultiChatMessage(ChatRole.SYSTEM, SYSTEM_PROMPT),
			new MultiChatMessage(ChatRole.USER, userPrompt)
		);

		Mono<String> response =  chatGPTClientService.multiChat(messages);

		return response.block();
	}
}
