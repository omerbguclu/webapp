package com.toplagel.webapp.service;

import com.toplagel.webapp.model.Product;
import com.toplagel.webapp.model.ShoppingCart;

public interface ShoppingCartService {

	void addProductToCart(ShoppingCart shoppingCart, Product product, Integer quantity);

	void deleteProductFromCart(ShoppingCart shoppingCart, Product product);

	void updateProductInCart(ShoppingCart shoppingCart, Product product, Integer quantity);

	Long calculateTotalPriceOfCart(ShoppingCart shoppingCart);

}
