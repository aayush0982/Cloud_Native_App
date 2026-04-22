package com.cg.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.demo.entity.Cart;
import com.cg.demo.repo.CartRepo;

@Service
public class CartServiceImpl implements CartServiceInterface {

	@Autowired
	private CartRepo cartRepo;

	@Override
	public Cart addToCart(Cart cart) {
		cartRepo.save(cart);
		return cart;
	}

	@Override
	public List<Cart> getAllCarts() {
		List<Cart> carts = cartRepo.findAll();
		return carts;
	}

	@Override
	public Cart getCartById(Integer cartId) {
		Optional<Cart> c = cartRepo.findById(cartId);
		Cart cart = null;
		if (c.isPresent()) {
			cart = c.get();
		}
		return cart;
	}

}
