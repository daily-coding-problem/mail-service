package com.dcp.config.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.List;

@Getter
@Configuration
@ConfigurationProperties(prefix = "properties")
@Validated
public class Properties {
	private final Mail mail = new Mail();
	private final Services services = new Services();
	private final Cors cors = new Cors();

	@Data
	@Validated
	public static class Mail {
		private String from;
	}

	@Data
	@Validated
	public static class Services {
		private final API api = new API();
		private final ChatGPT chatGPT = new ChatGPT();

		@Data
		@Validated
		public static class API {
			private String url;
		}

		@Data
		@Validated
		public static class ChatGPT {
			private String url;
		}
	}

	@Setter
	public static class Cors {
		private String allowedOrigins = "*";

		public List<String> getAllowedOrigins() {
			if (allowedOrigins == null || allowedOrigins.isBlank()) {
				return List.of("*");
			}
			return Arrays.asList(allowedOrigins.split(","));
		}
	}
}
