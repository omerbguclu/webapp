package com.toplagel.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toplagel.webapp.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	Company findByEmail(String email);
}
