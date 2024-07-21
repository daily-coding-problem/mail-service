package com.dcp.mail_service.v1.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mail")
public interface MailController {
	@GetMapping("/problem/{slug}")
	@ResponseBody
	String getEmailContentForProblem(
		@PathVariable String slug,
		@RequestParam(name = "provide_solution", required = false, defaultValue = "false") boolean provideSolution
	);

	@GetMapping("/problem/random")
	@ResponseBody
	String getEmailContentForProblem(
		@RequestParam(name = "provide_solution", required = false, defaultValue = "false") boolean provideSolution
	);
}
