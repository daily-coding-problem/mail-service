package com.dcp.ap_service.v1.services;

import com.dcp.ap_service.v1.entities.User;
import com.dcp.ap_service.v1.entities.Problem;
import com.dcp.ap_service.v1.entities.StudyPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APIService {
	private final ProblemService problemService;
	private final StudyPlanService studyPlanService;
	private final UserService userService;

	@Autowired
	public APIService(ProblemService problemService, StudyPlanService studyPlanService, UserService userService) {
		this.problemService = problemService;
		this.studyPlanService = studyPlanService;
		this.userService = userService;
	}

	public List<Problem> getAllProblems() {
		return problemService.getAllProblems();
	}

	public Problem getProblemBySlug(String slug) {
		return problemService.getProblemBySlug(slug);
	}

	public Problem createProblem(Problem problem) {
		return problemService.createProblem(problem);
	}

	public List<StudyPlan> getAllStudyPlans() {
		return studyPlanService.getAllStudyPlans();
	}

	public StudyPlan getStudyPlanBySlug(String slug) {
		return studyPlanService.getStudyPlanBySlug(slug);
	}

	public StudyPlan createStudyPlan(StudyPlan studyPlan) {
		return studyPlanService.createStudyPlan(studyPlan);
	}

	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	public User getUserByEmail(String email) {
		return userService.getUserByEmail(email);
	}

	public List<User> getAllPremiumUsers() {
		return userService.getAllPremiumUsers();
	}

	public Problem getRandomProblem() {
		return problemService.getRandomProblem();
	}
}
