package com.toplagel.webapp.service;

import com.toplagel.webapp.entity.Customer;

public interface CustomerService {
	
	void save(Customer customer);
	
	Customer findByEmail(String email);

}
