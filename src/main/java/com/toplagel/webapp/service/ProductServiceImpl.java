package com.toplagel.webapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toplagel.webapp.entity.Product;
import com.toplagel.webapp.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> getProduct(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}

	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
	}





}
