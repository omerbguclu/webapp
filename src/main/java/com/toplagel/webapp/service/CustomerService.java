package com.toplagel.webapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.toplagel.webapp.entity.Customer;

public interface CustomerService extends UserDetailsService{
	
	void save(Customer customer);
	
	Customer findByEmail(String email);
	
	public List<Customer> getCustomers();
	
	Customer findById(Long id);

}
