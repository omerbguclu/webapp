package com.toplagel.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toplagel.webapp.model.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>{

}
