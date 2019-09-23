package com.toplagel.webapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.toplagel.webapp.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

	Optional<Product> findById(Long id);

	List<Product> findAll();
}
