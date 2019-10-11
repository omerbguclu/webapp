package com.toplagel.webapp.service;

import java.util.List;

import com.toplagel.webapp.entity.Product;

public interface ProductService {

	List<Product> getProducts();

	Product findById(Long id);

	void save(Product product);

	void delete(Long id);

}
