package com.toplagel.webapp.model;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

	private Map<Product, Integer> products = new HashMap<Product, Integer>();
	private Integer totalPrice;

	public Map<Product, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

}
