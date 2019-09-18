package com.toplagel.webapp.service;

import java.util.List;

import com.toplagel.webapp.entity.Customer;

public interface CustomerService {
	
	void save(Customer customer);
	
	Customer findByEmail(String email);
	
	public List<Customer> getCustomers();

}
