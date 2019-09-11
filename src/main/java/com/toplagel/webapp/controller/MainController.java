package com.toplagel.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/login")
	public String root() {
		return "index";
	}
	
	@GetMapping("/company-login")
	public String loginForCompany() {
		return "company-login";
	}
	
	@GetMapping("/customer-login")
	public String loginForCustomer() {
		return "customer-login";
	}
	
	@GetMapping("/company-register")
	public String registerForCompany() {
		return "company-register";
	}
	
	@GetMapping("/customer-register")
	public String registerForCustomer() {
		return "customer-register";
	}
}
