package com.dcp.ap_service.v1.services;

import com.dcp.ap_service.v1.entities.StudyPlan;

import java.util.List;

public interface StudyPlanService {
	List<StudyPlan> getAllStudyPlans();
	StudyPlan getStudyPlanBySlug(String slug);
	StudyPlan createStudyPlan(StudyPlan studyPlan);
}
