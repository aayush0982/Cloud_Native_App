package com.cg.demo.entities;

//import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProductPricing {
	@Id
	private Integer productId;
	private double productPrice;

	public ProductPricing() {
	}

	public ProductPricing(Integer productId, double productPrice) {
		this.productId = productId;
		this.productPrice = productPrice;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "ProductPricing [productId=" + productId + ", productPrice=" + productPrice + "]";
	}

}
