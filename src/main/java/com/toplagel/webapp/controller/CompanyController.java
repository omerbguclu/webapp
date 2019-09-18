package com.toplagel.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toplagel.webapp.entity.Customer;
import com.toplagel.webapp.service.CustomerService;

@Controller
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("/add-customer")
	public String addCustomer(Model model) {
	    List<Customer> listCustomers = customerService.getCustomers();
	    model.addAttribute("listCustomers", listCustomers);
		return "company/add-customer";
	}

}
