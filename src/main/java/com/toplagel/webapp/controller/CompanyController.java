package com.toplagel.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toplagel.webapp.entity.Company;
import com.toplagel.webapp.entity.Product;
import com.toplagel.webapp.service.CompanyService;

@Controller
@RequestMapping("/company")
public class CompanyController extends ControllerCommon {

	@Autowired
	private CompanyService companyService;

	@GetMapping
	public String home(Model model) {
		Company company = companyService.findByEmail(getActiveLoggedUserEmail());
		model.addAttribute("productList", company.getProducts());
		model.addAttribute("listCustomers", company.getCustomers());
		model.addAttribute("productModal", new Product());
		return "welcome";
	}

	@GetMapping("/company-login")
	public String loginForCompany(Model model) {
		System.out.println(getActiveLoggedUserEmail());
		if (getActiveLoggedUserEmail() == "anonymousUser" || getActiveLoggedUserEmail() == null) {
			return "company-login";
		} else {
			if (getActiveLoggedUserRole() == null || getActiveLoggedUserRole().contains("ROLE_COMPANY")) {
				return "redirect:/company";
			} else {
				return "redirect:/customer";
			}
		}
	}

	@GetMapping("/company-register")
	public String registerForCompany(Model model) {
		model.addAttribute("company", new Company());
		return "company-register";
	}

	@PostMapping("/company-register")
	public String registerForCompanyPost(@ModelAttribute Company company) {
		companyService.save(company);
		return "company-login";
	}
}
