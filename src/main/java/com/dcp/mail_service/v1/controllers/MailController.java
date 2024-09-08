package com.dcp.mail_service.v1.controllers;

import com.dcp.api_service.v1.entities.User;
import com.dcp.mail_service.v1.entities.Email;
import com.resend.core.exception.ResendException;
import org.springframework.validation.annotation.Validated;
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
	String getEmailContentForRandomProblem(
		@RequestParam(name = "provide_solution", required = false, defaultValue = "false") boolean provideSolution
	);

	@PostMapping("/problem/{slug}")
	@ResponseBody
	String getEmailContentForProblemForUser(@PathVariable String slug, @RequestBody @Validated User user);

	@PostMapping("/send")
	@ResponseBody
	void sendEmail(@RequestBody @Validated Email email) throws ResendException;
}
