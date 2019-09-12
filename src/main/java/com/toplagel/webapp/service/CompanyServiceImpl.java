package com.toplagel.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.toplagel.webapp.entity.Company;
import com.toplagel.webapp.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void save(Company company) {
		
		company.setPassword(bCryptPasswordEncoder.encode(company.getPassword()));
		companyRepository.save(company);
	}

	@Override
	public Company findByEmail(String email) {
		return companyRepository.findByEmail(email);
	}

}
