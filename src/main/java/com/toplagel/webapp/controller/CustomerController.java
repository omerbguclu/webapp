package com.toplagel.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toplagel.webapp.entity.Customer;
import com.toplagel.webapp.service.CustomerServiceImpl;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	@GetMapping("/customer-login")
	public String loginForCustomer() {
		return "customer-login";
	}

	@GetMapping("/customer-register")
	public String registerForCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer-register";
	}

	@PostMapping("/customer-register")
	public String registerForCustomerPost(@ModelAttribute Customer customer) {
		customerServiceImpl.save(customer);
		return "index";
	}

	public String getActiveLoggedUserEmail() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		} else {
			return principal.toString();
		}

	}

}
