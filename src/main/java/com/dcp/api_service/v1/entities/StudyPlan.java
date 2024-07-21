package com.dcp.api_service.v1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudyPlan {
	private Long id;
	private String slug;
	private String name;
	private String description;
}
