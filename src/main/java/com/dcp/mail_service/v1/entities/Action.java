package com.dcp.mail_service.v1.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Action {
	UNKNOWN("UNKNOWN", "Unknown");

	public final String label;
	@Getter
	public final String subject;

	Action(String label, String subject) {
		this.label = label;
		this.subject = subject;
	}

	@Override
	public String toString() {
		return this.label;
	}
}
