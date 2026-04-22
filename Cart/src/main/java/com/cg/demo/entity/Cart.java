package com.cg.demo.entity;

//import java.util.HashMap;
import java.util.Map;

import com.cg.demo.modal.Catalogue;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;

@Entity
public class Cart {
	@Id
	private Integer cartId;
	@ElementCollection(fetch = FetchType.EAGER)
	private Map<Integer, Catalogue> products;

	public Cart() {
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Map<Integer, Catalogue> getProducts() {
		return products;
	}

	public void setProducts(Map<Integer, Catalogue> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", products=" + products + "]";
	}

}
