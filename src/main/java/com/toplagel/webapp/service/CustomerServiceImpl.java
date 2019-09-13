package com.toplagel.webapp.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.toplagel.webapp.entity.Customer;
import com.toplagel.webapp.entity.Role;
import com.toplagel.webapp.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void save(Customer customer) {

		customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
		customer.setRoles(Arrays.asList(new Role("ROLE_CUSTOMER")));
		customerRepository.save(customer);
	}

	@Override
	public Customer findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

}
