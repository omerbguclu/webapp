package com.toplagel.webapp.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toplagel.webapp.model.Customer;
import com.toplagel.webapp.model.Product;
import com.toplagel.webapp.model.ShoppingCart;
import com.toplagel.webapp.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController extends ControllerCommon {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public String home(Model model) {
		Customer customer = customerService.findByEmail(getActiveLoggedUserEmail());
		Collection<Product> products = new ArrayList<Product>();
		customer.getCompanies().forEach(x -> x.getProducts().forEach(e -> products.add(e)));
		model.addAttribute("listCompanies", customer.getCompanies());
		model.addAttribute("listProducts", products);
		model.addAttribute("listShoppingCartItems", customer.getShoppingCart().getProducts());
		return "welcome";
	}

	@GetMapping("/customer-login")
	public String loginForCustomer(Model model) {
		System.out.println(getActiveLoggedUserEmail());
		if (getActiveLoggedUserEmail() == "anonymousUser" || getActiveLoggedUserEmail() == null) {
			return "customer-login";
		} else {
			if (getActiveLoggedUserRole() == null || getActiveLoggedUserRole().contains("ROLE_COMPANY")) {
				return "redirect:/company";
			} else {
				return "redirect:/customer";
			}
		}
	}

	@GetMapping("/customer-register")
	public String registerForCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer-register";
	}

	@PostMapping("/customer-register")
	public String registerForCustomerPost(@ModelAttribute Customer customer) {
		customer.setShoppingCart(new ShoppingCart());
		customerService.save(customer);
		return "index";
	}

}
