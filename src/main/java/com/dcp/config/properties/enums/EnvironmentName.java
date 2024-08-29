package com.dcp.config.properties.enums;

import lombok.Getter;

@Getter
public enum EnvironmentName {
	DEVELOPMENT("development"),
	TEST("test"),
	PRODUCTION("production");

	private final String value;

	EnvironmentName(String value) {
		this.value = value;
	}
}
