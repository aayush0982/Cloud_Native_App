package com.cg.demo.entity;

import java.util.List;




public class Recommendation {

	private String productCategory;
	private List<Integer> productIds;

	public Recommendation() {
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public List<Integer> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Integer> productIds) {
		this.productIds = productIds;
	}

	@Override
	public String toString() {
		return "Recommendation [productCategory=" + productCategory + ", productIds=" + productIds + "]";
	}

}
