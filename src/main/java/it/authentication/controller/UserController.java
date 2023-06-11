package it.authentication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@GetMapping("/api/admin")
	@PreAuthorize("hasAuthority('Admin')")
	public String userAccess() {
		return ">>> Admin Contents!";
	}

	@GetMapping("/api/user")
	@PreAuthorize("hasAuthority('User')")
	public String projectManagementAccess() {
		return ">>> User Contents";
	}

}
