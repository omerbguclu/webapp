package com.toplagel.webapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toplagel.webapp.entity.Product;
import com.toplagel.webapp.service.ProductService;

@RestController
@RequestMapping("/company")
public class ProductRestController {
	
	// Autowire the CustomerService
	@Autowired
	private ProductService productService;

	// Add mapping for GET /customers
	@GetMapping("/products")
	public List<Product> getCustomers() {
		return productService.getProducts();
	}

	@GetMapping("/products/{customerId}")
	public Optional<Product> getCustomer(@PathVariable Long customerId) throws Exception {

		Optional<Product> theCustomer = productService.getProduct(customerId);

		if (theCustomer == null)
			throw new Exception("Customer id not found - " + customerId);

		return theCustomer;
	}

	// Add mapping for POST /customers - add new customer
	@PostMapping("/products")
	public Product addCustomer(@RequestBody Product theCustomer) {

		// also just in case the pass an id in JSON ... set id to 0
		// this is force a save of new item ... instead of update

		theCustomer.setId((long) 0);
		productService.save(theCustomer);

		return theCustomer;
	}

	// Add mapping for PUT /customers - update existing customer
	@PutMapping("/products")
	public Product updateCustomer(@RequestBody Product theCustomer) {

		productService.save(theCustomer);

		return theCustomer;
	}

	// Add mapping for DELETE /customers/{customerId} - delete existing customer
	@DeleteMapping("/products/{customerId}")
	public String deleteCustomer(@PathVariable Long customerId) throws Exception {

		Optional<Product> tempCustomer = productService.getProduct(customerId);

		// throw an exception if it is null
		if (tempCustomer == null)
			throw new Exception("Customer id not found - " + customerId);
		
		productService.delete(customerId);

		return "Deleted customer id - " + customerId;
	}

}
