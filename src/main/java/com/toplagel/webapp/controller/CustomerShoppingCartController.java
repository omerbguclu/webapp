package com.toplagel.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toplagel.webapp.model.Product;
import com.toplagel.webapp.model.ShoppingCart;

@Controller
@RequestMapping("/customer")
public class CustomerShoppingCartController extends ControllerCommon {

	//@Autowired
	//private ShoppingCartService shoppingCartService;

	@RequestMapping("/add-product-to-cart/{id}")
	public String addProducttoCart(Product product,ShoppingCart shoppingCart) {
		//ShoppingCart thisShoppingCart = shoppingCartService.addProductToCart(shoppingCart, product, 1);
		return "company/add-product";
	}

	@RequestMapping("/delete-product-from-cart/{id}")
	public String deleteProductFromCart(Product product) {

		return "company/add-product";
	}

}
