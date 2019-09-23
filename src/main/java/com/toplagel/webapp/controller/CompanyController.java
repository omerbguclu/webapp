package com.toplagel.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toplagel.webapp.entity.Company;
import com.toplagel.webapp.entity.Customer;
import com.toplagel.webapp.service.CompanyService;
import com.toplagel.webapp.service.CompanyServiceImpl;
import com.toplagel.webapp.service.CustomerService;

@Controller
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CompanyServiceImpl companyServiceImpl;

	@GetMapping
	public String home(Model model) {
		Company company = companyService.findByEmail(getActiveLoggedUserEmail());
		model.addAttribute("listCustomers", company.getCustomers());
		return "company/welcome";
	}

	@GetMapping("/company-login")
	public String loginForCompany(Model model) {
		return "company-login";
	}

	@GetMapping("/company-register")
	public String registerForCompany(Model model) {
		model.addAttribute("company", new Company());
		return "company-register";
	}

	@PostMapping("/company-register")
	public String registerForCompanyPost(@ModelAttribute Company company) {
		companyServiceImpl.save(company);
		return "index";
	}

	@GetMapping("/add-customer")
	public String addCustomer(Model model) {
		List<Customer> listCustomers = customerService.getCustomers();
		model.addAttribute("listCustomers", listCustomers);
		return "company/add-customer";
	}

	@PostMapping("/add-customer/{id}")
	public String showAllCustomers(@PathVariable(name = "id") Long id) {
		Customer customer = customerService.findById(id);
		Company company = companyService.findByEmail(getActiveLoggedUserEmail());
		if (company.getCustomers().contains(customer)) {
			System.out.println("already added");
		} else {
			companyService.addCustomer(company, customer);
		}
		return "redirect:/";
	}

	@PostMapping("/delete-customer/{id}")
	public String showAllActiveCompanyCustomers(@PathVariable(name = "id") Long id) {
		Customer customer = customerService.findById(id);
		Company company = companyService.findByEmail(getActiveLoggedUserEmail());
		if (company.getCustomers().contains(customer)) {
			companyService.deleteCustomer(company, customer);
		} else {
			System.out.println("There is no user with this name");
		}
		return "redirect:/";
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
