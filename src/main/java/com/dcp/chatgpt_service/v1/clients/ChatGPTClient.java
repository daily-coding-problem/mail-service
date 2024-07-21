package com.dcp.chatgpt_service.v1.clients;

import com.dcp.chatgpt_service.v1.entities.MultiChatMessage;
import com.dcp.config.properties.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ChatGPTClient {

	private final WebClient webClient;

	@Autowired
	public ChatGPTClient(WebClient.Builder webClientBuilder, Properties properties) {
		String baseUrl = properties.getServices().getChatGPT().getUrl();
		this.webClient = webClientBuilder.baseUrl(baseUrl).build();
	}

	public Mono<String> multiChat(List<MultiChatMessage> messages) {
		return webClient.post()
			.uri("/api/v1/chatgpt/multiChat")
			.bodyValue(messages)
			.retrieve()
			.bodyToMono(String.class);
	}
}
