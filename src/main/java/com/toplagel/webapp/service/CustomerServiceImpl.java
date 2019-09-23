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

import com.toplagel.webapp.entity.Customer;
import com.toplagel.webapp.entity.Role;
import com.toplagel.webapp.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoderForCustomer;

	@Override
	public Customer findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	@Override
	public void save(Customer customer) {

		customer.setPassword(bCryptPasswordEncoderForCustomer.encode(customer.getPassword()));
		customer.setRoles(Arrays.asList(new Role("ROLE_CUSTOMER")));
		customerRepository.save(customer);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null){
        	System.out.println(email+" user not found");
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        System.out.println(customer.toString());
        return new org.springframework.security.core.userdetails.User(customer.getEmail(),
        		customer.getPassword(),
                mapRolesToAuthorities(customer.getRoles()));
	}

	@Override
	public List<Customer> getCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}

	@Override
	public Customer findById(Long id) {
		return customerRepository.findById(id).get();
	}
	
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }

}
