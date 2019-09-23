package com.toplagel.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.toplagel.webapp.entity.Product;
import com.toplagel.webapp.repository.ProductRepository;

public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
	}

}
