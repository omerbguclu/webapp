package com.toplagel.webapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.toplagel.webapp.model.Product;
import com.toplagel.webapp.model.ShoppingCart;
import com.toplagel.webapp.service.ShoppingCartServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {

	@InjectMocks
	private ShoppingCartServiceImpl shoppingCartService;

	@Test
	public void shoppingCartSumTest() {
		ShoppingCart shoppingCart = new ShoppingCart();
		Product a = new Product();
		a.setPrice(10L);
		Product b = new Product();
		b.setPrice((long) 25);
		Product c = new Product();
		c.setPrice((long) 30);

		shoppingCartService.addProductToCart(shoppingCart, a);
		shoppingCartService.addProductToCart(shoppingCart, b);
		shoppingCartService.addProductToCart(shoppingCart, c);

		System.out.println(shoppingCartService.calculateTotalPriceOfCart(shoppingCart));
		System.out.println(shoppingCartService.updateProductQuantity(shoppingCart, b, 3));
		System.out.println(shoppingCartService.calculateTotalPriceOfCart(shoppingCart));

	}

}
