package it.authentication.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.authentication.model.dto.LoginDTO;
import it.authentication.model.dto.ResponseDTO;
import it.authentication.model.dto.SignUpDTO;
import it.authentication.service.AuthService;


@CrossOrigin
@RestController
@RequestMapping("api/public/auth")
public class AuthController {
	@Autowired
	AuthService as;

	@PostMapping("/signin")
	public ResponseDTO authenticateUser(@Valid @RequestBody LoginDTO loginRequest,
			@RequestHeader(value = "User-Agent") String userAgent, RedirectAttributes redirectAttributes, Model model) {
		return as.authentication(loginRequest.getUsername(), loginRequest.getPassword(), userAgent);
	}

	@PostMapping("/signup")
	public ResponseDTO registerUser(@Valid @RequestBody SignUpDTO signUpRequest) {
		return as.signUp(signUpRequest);
	}
}
