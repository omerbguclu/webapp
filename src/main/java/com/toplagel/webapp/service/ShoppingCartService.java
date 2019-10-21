package com.toplagel.webapp.service;

import com.toplagel.webapp.model.Product;
import com.toplagel.webapp.model.ShoppingCart;

public interface ShoppingCartService {

	void addProductToCart(ShoppingCart shoppingCart, Product product);

	void deleteProductFromCart(ShoppingCart shoppingCart, Product product);

	Long calculateTotalPriceOfCart(ShoppingCart shoppingCart);

}
