package com.toplagel.webapp.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.toplagel.webapp.entity.Company;
import com.toplagel.webapp.entity.Role;
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
		// company.setRoles(new HashSet<>(roleRepository.findAll()));
		company.setRoles(Arrays.asList(new Role("ROLE_COMPANY")));
		companyRepository.save(company);
	}

	@Override
	public Company findByEmail(String email) {
		return companyRepository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Company company = companyRepository.findByEmail(email);
        if (company == null){
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        return new org.springframework.security.core.userdetails.User(company.getEmail(),
                company.getPassword(),
                mapRolesToAuthorities(company.getRoles()));
	}
	
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }

}
