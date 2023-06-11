package it.authentication.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.authentication.model.dto.SignUpDTO;

@Controller
@RequestMapping("/api/public/auth")
public class NewUserController {
	
    @GetMapping("/register")
    public String showForm(Model model) {
    	SignUpDTO user = new SignUpDTO();
         model.addAttribute("user", user);
         
        List<String> listProfession = Arrays.asList("User", "Admin");
        model.addAttribute("listProfession", listProfession);
         
        return "register_form";
    }

}
