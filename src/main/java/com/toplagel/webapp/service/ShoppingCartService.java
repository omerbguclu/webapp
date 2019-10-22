package com.toplagel.webapp.service;

import com.toplagel.webapp.model.Product;
import com.toplagel.webapp.model.ShoppingCart;

public interface ShoppingCartService {

	ShoppingCart addProductToCart(ShoppingCart shoppingCart, Product product, Integer quantity);

	ShoppingCart deleteProductFromCart(ShoppingCart shoppingCart, Product product);

	ShoppingCart updateProductInCart(ShoppingCart shoppingCart, Product product, Integer quantity);

	Long calculateTotalPriceOfCart(ShoppingCart shoppingCart);

}
