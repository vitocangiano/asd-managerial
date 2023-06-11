package it.authentication.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.authentication.model.dto.ResponseDTO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public")
public class DefaultController {
	@RequestMapping
	public ResponseDTO index() {
		ResponseDTO response = new ResponseDTO();
		response.setStatus(true);
		response.getContent().put("test", "test");
		return response;
	}

}
