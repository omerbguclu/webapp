package com.toplagel.webapp.service;

import com.toplagel.webapp.entity.Company;

public interface CompanyService {
	
	void save(Company company);
	
	Company findByEmail(String email);

}
