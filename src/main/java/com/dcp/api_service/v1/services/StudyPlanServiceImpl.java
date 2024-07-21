package com.dcp.api_service.v1.services;

import com.dcp.api_service.v1.entities.StudyPlan;
import com.dcp.config.properties.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class StudyPlanServiceImpl implements StudyPlanService {
	private final RestTemplate restTemplate;
	private final Properties properties;

	@Autowired
	public StudyPlanServiceImpl(RestTemplate restTemplate, Properties properties) {
		this.restTemplate = restTemplate;
		this.properties = properties;
	}

	@Override
	public List<StudyPlan> getAllStudyPlans() {
		String url = properties.getServices().getApi().getUrl() + "/api/v1/study-plans";
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
		StudyPlan[] studyPlans = restTemplate.getForObject(uriBuilder.toUriString(), StudyPlan[].class);

		if (studyPlans == null) {
			return Collections.emptyList();
		}

		return Arrays.asList(studyPlans);
	}

	@Override
	public StudyPlan getStudyPlanBySlug(String slug) {
		String url = properties.getServices().getApi().getUrl() + "/api/v1/study-plans/" + slug;
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);

		return restTemplate.getForObject(uriBuilder.toUriString(), StudyPlan.class);
	}

	@Override
	public StudyPlan createStudyPlan(StudyPlan studyPlan) {
		String url = properties.getServices().getApi().getUrl() + "/api/v1/study-plans";

		return restTemplate.postForObject(url, studyPlan, StudyPlan.class);
	}
}
