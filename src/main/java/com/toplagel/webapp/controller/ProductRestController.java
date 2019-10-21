package com.toplagel.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toplagel.webapp.model.Product;
import com.toplagel.webapp.service.ProductService;

@RestController
@RequestMapping("/company")
public class ProductRestController {
	
	// Autowire the CustomerService
	@Autowired
	private ProductService productService;

	// Add mapping for GET /customers
	@GetMapping("/products")
	public List<Product> getProducts() {
		return productService.getProducts();
	}

	@GetMapping("/products/{customerId}")
	public Product getProduct(@PathVariable Long productId) throws Exception {

		Product product = productService.findById(productId);

		if (product == null)
			throw new Exception("Product id not found - " + productId);

		return product;
	}

	// Add mapping for POST /customers - add new customer
	@PostMapping("/products")
	public Product addCustomer(@RequestBody Product product) {

		// also just in case the pass an id in JSON ... set id to 0
		// this is force a save of new item ... instead of update

		product.setId((long) 0);
		productService.save(product);

		return product;
	}

	// Add mapping for PUT /customers - update existing customer
	@PutMapping("/products")
	public Product updateCustomer(@RequestBody Product product) {

		productService.save(product);

		return product;
	}

	// Add mapping for DELETE /customers/{customerId} - delete existing customer
	@DeleteMapping("/products/{customerId}")
	public String deleteCustomer(@PathVariable Long productId) throws Exception {

		Product product = productService.findById(productId);

		// throw an exception if it is null
		if (product == null)
			throw new Exception("Customer id not found - " + productId);
		
		productService.delete(productId);

		return "Deleted customer id - " + productId;
	}

}
