package com.cg.demo.modal;


public class ProductInventory {

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
