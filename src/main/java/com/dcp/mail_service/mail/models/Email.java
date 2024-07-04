package com.dcp.mail_service.mail.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Map;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
@Setter
public class Email {

	private String recipient;
	private String from;
	private String subject;
	private String text;
	private ActionEnum action;
	private Map<String, Object> properties;

	public Email() {}

	public Email(
		String recipient,
		String from,
		String subject,
		String text,
		ActionEnum action,
		Map<String, Object> properties) {
		this.recipient = recipient;
		this.from = from;
		this.subject = subject;
		this.text = text;
		this.action = action;
		this.properties = properties;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
