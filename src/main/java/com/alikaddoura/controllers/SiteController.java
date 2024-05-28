package com.alikaddoura.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SiteController {
	
	@GetMapping("/home")
	public String homePage(Model model) {
		model.addAttribute("message", "have a greate day");
		
		return "home.html";
	}
}

