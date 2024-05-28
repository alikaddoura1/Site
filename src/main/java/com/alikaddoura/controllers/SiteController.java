package com.alikaddoura.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SiteController {
	
	@GetMapping("/home")
	public String homePage() {
		
		return "home.html";
	}
}
