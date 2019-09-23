package com.toplagel.webapp.service;

import org.springframework.stereotype.Service;

import com.toplagel.webapp.entity.Product;

@Service
public interface ProductService {
	Product save(Product product);

	void delete(Long id);

}
