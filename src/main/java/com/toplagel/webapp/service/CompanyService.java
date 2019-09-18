package com.toplagel.webapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.toplagel.webapp.entity.Company;
import com.toplagel.webapp.entity.Customer;

public interface CompanyService extends UserDetailsService{
	
	Company save(Company company);
	
	Company findByEmail(String email);
	
	Company addCustomer(Company company,Customer customer);
	
	Company deleteCustomer(Company company,Customer customer);

}
