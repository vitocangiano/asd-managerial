package it.authentication.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prova")
public class ProvaController {
	
	@PostMapping("/read" )
	//@PreAuthorize("hasAuthority('Admin')")
	public String prova() {
		return"prova";
	}
	@GetMapping("/set")
	public String setCookie(HttpServletResponse response) {
	    // set a new cookie
	    Cookie cookie = new Cookie("color", "blue");
	    cookie.setMaxAge(86000);
	    cookie.setSecure(true);
	    cookie.setHttpOnly(false);
	    cookie.setPath("/");
	    cookie.setDomain("localhost");
	    // add cookie in server response
	    response.addCookie(cookie);

	    return "Spring Boot Cookies";
	}
	/*public Cookie setCookie(String jwt) {
		 
		Cookie jwtTokenCookie = new Cookie("user-id", jwt);
		jwtTokenCookie.setMaxAge(86400);
		jwtTokenCookie.setSecure(true);
		jwtTokenCookie.setHttpOnly(true);
		jwtTokenCookie.setPath("/");
		
	

	        return jwtTokenCookie;

	    }*/


}
