package com.cg.demo.service;

import java.util.List;

import com.cg.demo.entities.ProductPricing;

public interface ProductPricingServiceInterface {
	public double getPriceByProductId(int productId);
	public void addProductPricing(ProductPricing pricing);
	public List<ProductPricing> getAllProductPricings();
}
