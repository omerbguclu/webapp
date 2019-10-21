package com.toplagel.webapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.toplagel.webapp.model.Product;
import com.toplagel.webapp.model.ShoppingCart;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Override
	public void addProductToCart(ShoppingCart shoppingCart, Product product) {
		List<Product> products = shoppingCart.getProducts();
		products.add(product);
		shoppingCart.setProducts(products);
	}

	@Override
	public void deleteProductFromCart(ShoppingCart shoppingCart, Product product) {
		List<Product> products = shoppingCart.getProducts();
		products.remove(product);
		shoppingCart.setProducts(products);
	}

	@Override
	public Long calculateTotalPriceOfCart(ShoppingCart shoppingCart) {
		return shoppingCart.getProducts().stream()
				.map(Product::getPrice)
				.reduce(Long::sum).get();
	}

}
