package com.cg.demo.modal;


public class ProductPricing {

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
