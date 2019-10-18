package com.toplagel.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toplagel.webapp.entity.Company;
import com.toplagel.webapp.entity.Customer;
import com.toplagel.webapp.service.CompanyService;
import com.toplagel.webapp.service.CustomerService;

@Controller
@RequestMapping("/company")
public class CompanyCustomerController extends ControllerCommon {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CompanyService companyService;

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
		return "redirect:/company";
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
		return "redirect:/company";
	}

}
