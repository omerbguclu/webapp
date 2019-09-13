package com.toplagel.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.toplagel.webapp.entity.Company;
import com.toplagel.webapp.entity.Customer;
import com.toplagel.webapp.service.CompanyServiceImpl;
import com.toplagel.webapp.service.CustomerServiceImpl;

@Controller
public class MainController {
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@Autowired
	private CompanyServiceImpl companyServiceImpl;

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/login")
	public String root() {
		return "index";
	}

	@GetMapping("/company-login")
	public String loginForCompany(Model model) {
		return "company-login";
	}

	@GetMapping("/customer-login")
	public String loginForCustomer() {
		return "customer-login";
	}

	@GetMapping("/company-register")
	public String registerForCompany(Model model) {
		model.addAttribute("company", new Company());
		return "company-register";
	}

	@GetMapping("/customer-register")
	public String registerForCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer-register";
	}

	@PostMapping("/company-register")
	public String registerForCompanyPost(@ModelAttribute Company company) {
		System.out.println(company);
		companyServiceImpl.save(company);
		return "index";
	}

	@PostMapping("/customer-register")
	public String registerForCustomerPost(@ModelAttribute Customer customer) {
		System.out.println(customer);
		customerServiceImpl.save(customer);
		return "index";
	}

}
