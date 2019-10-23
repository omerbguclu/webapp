package com.toplagel.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toplagel.webapp.model.Customer;
import com.toplagel.webapp.model.Product;
import com.toplagel.webapp.model.ShoppingCart;
import com.toplagel.webapp.service.CustomerService;
import com.toplagel.webapp.service.ProductService;
import com.toplagel.webapp.service.ShoppingCartService;

@Controller
@RequestMapping("/customer")
public class CustomerShoppingCartController extends ControllerCommon {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/add-product-to-cart/{id}")
	public String addProducttoCart(@PathVariable(name = "id") Long id) {
		Customer customer = customerService.findByEmail(getActiveLoggedUserEmail());
		Product product = productService.findById(id);
		ShoppingCart shoppingCart = customer.getShoppingCart();
		shoppingCartService.addProductToCart(shoppingCart, product, 1);
		customerService.update(customer);;
		return "redirect:/customer";
	}

	@RequestMapping("/delete-product-from-cart/{id}")
	public String deleteProductFromCart(@PathVariable(name = "id") Long id) {
		Customer customer = customerService.findByEmail(getActiveLoggedUserEmail());
		Product product = productService.findById(id);
		ShoppingCart shoppingCart = customer.getShoppingCart();
		shoppingCartService.deleteProductFromCart(shoppingCart, product);
		customerService.update(customer);;
		return "redirect:/customer";
	}

}
