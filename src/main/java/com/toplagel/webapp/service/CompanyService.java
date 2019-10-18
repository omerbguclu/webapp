package com.toplagel.webapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.toplagel.webapp.entity.Company;
import com.toplagel.webapp.entity.Customer;
import com.toplagel.webapp.entity.Product;

public interface CompanyService extends UserDetailsService{
	
	Company save(Company company);
	
	Company findByEmail(String email);
	
	Company addCustomer(Company company,Customer customer);
	
	Company deleteCustomer(Company company,Customer customer);

	Company addProduct(Company company, Product product);

	Company deleteProduct(Company company, Product product);

}
