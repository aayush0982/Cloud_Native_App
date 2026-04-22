package com.cg.demo.service;

import java.util.List;

import com.cg.demo.entities.ProductDetails;

public interface ProductDetailsServiceInterface {
	public void addProductDetails(ProductDetails productDetails);

	public ProductDetails getProductDetailsByName(String productName);
	
	public List<ProductDetails> getAllProductDetails();

	ProductDetails getProductDetailsById(Integer productId);

	List<ProductDetails> getProductDetailsByCategory(String productCategory);
}
