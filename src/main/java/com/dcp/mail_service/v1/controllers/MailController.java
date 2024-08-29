package com.dcp.mail_service.v1.controllers;

import com.dcp.mail_service.v1.entities.Email;
import com.resend.core.exception.ResendException;
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

	@PostMapping("/send/problem/{slug}")
	void sendEmail(@PathVariable(name = "slug") String slug, @RequestBody Email email) throws ResendException;

	@PostMapping("/send/problem/random")
	void sendEmail(@RequestBody Email email) throws ResendException;
}
