package com.dcp.api_service.v1.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Long id;

	private String email;

	private boolean isPremium;

	private String timezone;

	private Timestamp createdAt;

	private String unsubscribeToken;
	private Timestamp unsubscribeTokenExpiresAt;
}
