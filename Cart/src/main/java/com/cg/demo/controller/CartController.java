package com.cg.demo.controller;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.demo.entity.Cart;
import com.cg.demo.entity.Recommendation;
import com.cg.demo.modal.Catalogue;
import com.cg.demo.service.CartServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class CartController {

	@Autowired
	private CartServiceImpl cartService;

	@Autowired
	RestTemplate restTemplate;

	static class CartRequest {
		public Integer cartId;
		public Integer productId;
	}

	@PostMapping("/carts")
	public List<Catalogue> addToCart(@RequestBody CartRequest request) {

		if (request.productId == null) {
			throw new RuntimeException("ProductId is required");
		}

		Catalogue product = restTemplate
				.getForObject("http://ProductCatalogue/catalogue/productids/" + request.productId, Catalogue.class);

		Cart cart = cartService.getCartById(request.cartId);

		if (cart == null) {
			cart = new Cart();
			cart.setCartId(request.cartId);
		}

		if (cart.getProducts() == null) {
			cart.setProducts(new HashMap<>());
		}

		cart.getProducts().put(product.getProductId(), product);

		cartService.addToCart(cart);

		Recommendation[] recommendationsArray = restTemplate.getForObject(
				"http://Recommendation/recommendations/" + product.getProductCategory(), Recommendation[].class);

		List<Catalogue> productNames = new ArrayList<>();

		if (recommendationsArray != null) {
			for (Recommendation rec : recommendationsArray) {
				if (rec.getProductIds() != null) {
					for (Integer id : rec.getProductIds()) {
						Catalogue c = restTemplate.getForObject("http://ProductCatalogue/catalogue/productids/" + id,
								Catalogue.class);
						if (c != null) {
							if (id != request.productId) {
								productNames.add(c);
							}

						}
					}
				}
			}
		}

		return productNames;
	}

	@GetMapping("/carts/{id}")
	@CircuitBreaker(name = "cartService", fallbackMethod = "fallbackGetCartById")
	public Cart getCartById(@PathVariable Integer id) {

		Cart cart = cartService.getCartById(id);

		if (cart == null) {
			throw new RuntimeException("Cart not found");
		}

		return cart;
	}

	public Cart fallbackGetCartById(Integer id, Throwable ex) {

		System.out.println("Circuit Breaker Fallback Triggered: " + ex.getMessage());

		Cart fallbackCart = new Cart();
		fallbackCart.setCartId(id);

		return fallbackCart;
	}
}
