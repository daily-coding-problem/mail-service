package com.dcp.ap_service.v1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Problem {
	private Long id;

	private Integer questionId;
	private String title;
	private String slug;
	private String content;
	private String difficulty;

	private List<String> topics;
	private List<String> companies;
	private List<String> hints;

	private String link;
}
