package com.cg.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProductInventory {
	@Id
	private Integer productId;
	private int productQuantity;

	public ProductInventory() {
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	@Override
	public String toString() {
		return "ProductInventory [productId=" + productId + ", productQuantity=" + productQuantity + "]";
	}

}
