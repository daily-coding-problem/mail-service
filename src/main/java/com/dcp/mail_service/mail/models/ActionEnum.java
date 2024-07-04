package com.dcp.mail_service.mail.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ActionEnum {
	UNKNOWN("UNKNOWN", "Unknown");

	public final String label;
	@Getter
	public final String subject;

	ActionEnum(String label, String subject) {
		this.label = label;
		this.subject = subject;
	}

	@Override
	public String toString() {
		return this.label;
	}
}
