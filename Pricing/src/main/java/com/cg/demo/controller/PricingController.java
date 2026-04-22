package com.cg.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.demo.entities.ProductPricing;
import com.cg.demo.service.ProductPricingServiceImpl;

@RestController
public class PricingController {

	@Autowired
	private ProductPricingServiceImpl pricingService;

	@GetMapping("/price/{productId}")
	public double getPrice(@PathVariable int productId) {
		return pricingService.getPriceByProductId(productId);
	}

	@PostMapping("/price")
	public ProductPricing addPrice(@RequestBody ProductPricing pricing) {
	    pricingService.addProductPricing(pricing);
	    return pricing;
	}

	@GetMapping("/price")
	public List<ProductPricing> getAllPrices() {
		return pricingService.getAllProductPricings();
	}

}
