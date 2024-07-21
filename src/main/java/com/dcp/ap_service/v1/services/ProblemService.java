package com.dcp.ap_service.v1.services;

import com.dcp.ap_service.v1.entities.Problem;

import java.util.List;

public interface ProblemService {
	List<Problem> getAllProblems();
	Problem getProblemBySlug(String slug);
	Problem createProblem(Problem problem);
	String getSolution(Problem problem);
	Problem getRandomProblem();
}
