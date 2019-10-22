package com.toplagel.webapp.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.toplagel.webapp.model.Product;
import com.toplagel.webapp.model.ShoppingCart;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Override
	public ShoppingCart addProductToCart(ShoppingCart shoppingCart, Product product, Integer quantity) {
		Map<Product, Integer> products = shoppingCart.getProducts();
		products.put(product, products.containsKey(product) ? products.get(product) + quantity : 1);
		shoppingCart.setProducts(products);
		return shoppingCart;
	}

	@Override
	public ShoppingCart deleteProductFromCart(ShoppingCart shoppingCart, Product product) {
		Map<Product, Integer> products = shoppingCart.getProducts();
		products.remove(product);
		shoppingCart.setProducts(products);
		return shoppingCart;
	}

	@Override
	public ShoppingCart updateProductInCart(ShoppingCart shoppingCart, Product product, Integer quantity) {
		Map<Product, Integer> products = shoppingCart.getProducts();
		if (products.containsKey(product))
			products.put(product, quantity);
		else
			System.out.println("There is no product with this name");
		shoppingCart.setProducts(products);
		return shoppingCart;
	}

	@Override
	public Long calculateTotalPriceOfCart(ShoppingCart shoppingCart) {
		return shoppingCart.getProducts().entrySet().stream().map(x -> x.getKey().getPrice() * x.getValue()).reduce(0L,
				Long::sum);
	}

	public void print(ShoppingCart shoppingCart) {
		shoppingCart.getProducts().entrySet().stream().forEach(System.out::println);
	}
}
