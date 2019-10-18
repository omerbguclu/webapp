package com.toplagel.webapp.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
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
import com.toplagel.webapp.entity.Product;
import com.toplagel.webapp.entity.Role;
import com.toplagel.webapp.repository.CompanyRepository;
import com.toplagel.webapp.repository.CustomerRepository;
import com.toplagel.webapp.repository.ProductRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;

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
		List<Customer> customers = company.getCustomers();
		customers.add(customer);
		Set<Company> companies = customer.getCompanies();
		companies.add(company);
		customer.setCompanies(companies);
		company.setCustomers(customers);
		customerRepository.save(customer);
		return companyRepository.save(company);
	}

	@Override
	public Company deleteCustomer(Company company, Customer customer) {
		List<Customer> customers = company.getCustomers();
		customers.remove(customer);
		Set<Company> companies = customer.getCompanies();
		companies.remove(company);
		customer.setCompanies(companies);
		company.setCustomers(customers);
		customerRepository.save(customer);
		return companyRepository.save(company);
	}

	@Override
	public Company addProduct(Company company,Product product) {
		Collection<Product> products = company.getProducts();
		products.add(product);
		company.setProducts(products);
		productRepository.save(product);
		return companyRepository.save(company);
	}
	
	@Override
	public Company deleteProduct(Company company,Product product) {
		Collection<Product> products = company.getProducts();
		products.remove(product);
		company.setProducts(products);
		productRepository.save(product);
		return companyRepository.save(company);
	}
}
