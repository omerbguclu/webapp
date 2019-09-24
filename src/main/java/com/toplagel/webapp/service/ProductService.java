package com.toplagel.webapp.service;

import java.util.List;
import java.util.Optional;

import com.toplagel.webapp.entity.Product;

public interface ProductService {

	List<Product> getProducts();

	Optional<Product> getProduct(Long id);

	void save(Product product);

	void delete(Long id);

}
