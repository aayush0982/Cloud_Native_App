package com.cg.demo.service;

import java.util.List;

import com.cg.demo.entity.Cart;

public interface CartServiceInterface {
	public Cart addToCart(Cart cart);

	public List<Cart> getAllCarts();
	
	public Cart getCartById(Integer cartId);
}
