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

import com.toplagel.webapp.entity.Company;
import com.toplagel.webapp.entity.Customer;
import com.toplagel.webapp.service.CompanyService;
import com.toplagel.webapp.service.CustomerService;
import com.toplagel.webapp.service.CustomerServiceImpl;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CompanyService companyService;
	
	@GetMapping
	public String home(Model model) {
		Customer customer = customerService.findByEmail(getActiveLoggedUserEmail());
		model.addAttribute("listCompanies", customer.getCompanies());
		return "welcome";
	}

	@GetMapping("/customer-login")
	public String loginForCustomer(Model model) {
		System.out.println(getActiveLoggedUserEmail());
		if (getActiveLoggedUserEmail() == "anonymousUser" || getActiveLoggedUserEmail() == null) {
			return "customer-login";
		}
		else {
			if(getActiveLoggedUserRole() == null || getActiveLoggedUserRole().contains("ROLE_COMPANY")) {
				Company company = companyService.findByEmail(getActiveLoggedUserEmail());
				model.addAttribute("listCustomers", company.getCustomers());
			}
			else{
				Customer customer = customerService.findByEmail(getActiveLoggedUserEmail());
				model.addAttribute("listCompanies", customer.getCompanies());
			}
			return "welcome";
		}
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
	
	public String getActiveLoggedUserRole() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getAuthorities().toString();
		} else {
			return null;
		}
	}

}
