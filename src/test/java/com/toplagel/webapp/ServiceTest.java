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
		a.setPrice(10L);a.setTitle("a");
		Product b = new Product();
		b.setPrice((long) 25);b.setTitle("b");
		Product c = new Product();
		c.setPrice((long) 30);c.setTitle("c");
		Product d = new Product();
		d.setPrice((long) 45);d.setTitle("d");
		
		shoppingCart.getProducts().put(a, 1);
		shoppingCart.getProducts().put(b, 1);
		shoppingCart.getProducts().put(c, 1);
		
		shoppingCartService.addProductToCart(shoppingCart, c, 1);
		shoppingCartService.addProductToCart(shoppingCart, a, 3);
		shoppingCartService.addProductToCart(shoppingCart, b, 2);
		//shoppingCartService.addProductToCart(shoppingCart, d, 2);
		
		shoppingCartService.print(shoppingCart);
		
		shoppingCartService.updateProductInCart(shoppingCart, a, 10);
		System.out.println("/*/*/*/*/*/*/t");
		shoppingCartService.print(shoppingCart);
		System.out.println(shoppingCartService.calculateTotalPriceOfCart(shoppingCart));
	}

}
