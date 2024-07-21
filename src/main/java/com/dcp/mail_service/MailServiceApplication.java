package com.dcp.mail_service;

import com.dcp.config.properties.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(Properties.class)
@SpringBootApplication(scanBasePackages = "com.dcp")
public class MailServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MailServiceApplication.class, args);
	}
}
