package com.toplagel.webapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.toplagel.webapp.entity.Company;

public interface CompanyService extends UserDetailsService{
	
	Company save(Company company);
	
	Company findByEmail(String email);

}
