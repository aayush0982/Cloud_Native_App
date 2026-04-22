package com.cg.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.demo.entities.ProductPricing;

public interface ProductPricingRepo extends JpaRepository<ProductPricing, Integer> {

	double getPriceByProductId(int productId);

}
