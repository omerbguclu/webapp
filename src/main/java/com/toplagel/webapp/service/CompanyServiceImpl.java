package com.toplagel.webapp.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.toplagel.webapp.entity.Company;
import com.toplagel.webapp.entity.Customer;
import com.toplagel.webapp.entity.Role;
import com.toplagel.webapp.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Company findByEmail(String email) {
		return companyRepository.findByEmail(email);
	}

	@Override
	public Company save(Company company) {

		company.setPassword(bCryptPasswordEncoder.encode(company.getPassword()));
		// company.setRoles(new HashSet<>(roleRepository.findAll()));
		company.setRoles(Arrays.asList(new Role("ROLE_COMPANY")));
		return companyRepository.save(company);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Company company = companyRepository.findByEmail(email);
        if (company == null){
        	System.out.println(email+" user not found");
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        System.out.println(company.toString());
        return new org.springframework.security.core.userdetails.User(company.getEmail(),
                company.getPassword(),
                mapRolesToAuthorities(company.getRoles()));
	}
	
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }

	@Override
	public Company addCustomer(Company company,Customer customer) {
		List<Customer> list = company.getCustomers();
		list.add(customer);
		company.setCustomers(list);
		return companyRepository.save(company);
	}

	@Override
	public Company deleteCustomer(Company company, Customer customer) {
		List<Customer> list = company.getCustomers();
		list.remove(customer);
		company.setCustomers(list);
		return companyRepository.save(company);
	}

}
