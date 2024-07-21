package com.dcp.config;

import com.dcp.config.properties.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	private final Environment env;
	private final Properties.Cors webSecurity;

	public WebSecurityConfig(Environment env, Properties properties) {
		this.env = env;
		this.webSecurity = properties.getCors();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		if (isTestProfile()) {
			http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
		} else {
			// Configure CORS
			http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

			// Configure headers disabling frame options and XSS protection
			http.headers(headers -> {
				headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
				headers.xssProtection(HeadersConfigurer.XXssConfig::disable);
			});

			// Disable CSRF
			http.csrf(AbstractHttpConfigurer::disable);
		}

		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(webSecurity.getAllowedOrigins());
		configuration.setAllowedMethods(List.of("POST", "GET"));
		configuration.setAllowedHeaders(Collections.singletonList("*"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

	private boolean isTestProfile() {
		for (String profile : env.getActiveProfiles()) {
			if (profile.equalsIgnoreCase("test")) {
				return true;
			}
		}

		return false;
	}
}
