package com.cg.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.demo.entities.ProductPricing;
import com.cg.demo.repo.ProductPricingRepo;

@Service
public class ProductPricingServiceImpl implements ProductPricingServiceInterface {

	@Autowired
	private ProductPricingRepo pricingRepo;

	

	@Override
	public void addProductPricing(ProductPricing pricing) {
	    pricingRepo.save(pricing);
	}

	@Override
	public List<ProductPricing> getAllProductPricings() {
		List<ProductPricing> pricings = pricingRepo.findAll();
		return pricings;
	}

	@Override
	public double getPriceByProductId(int productId) {
	    ProductPricing pricing = pricingRepo.findById(productId).orElse(null);

	    if (pricing == null) {
	        throw new RuntimeException("Product not found");
	    }

	    return pricing.getProductPrice();
	}

}
