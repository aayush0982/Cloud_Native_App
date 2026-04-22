package com.cg.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.demo.entities.ProductDetails;

public interface ProductDetailsRepo extends JpaRepository<ProductDetails, Integer> {

	ProductDetails findByProductName(String productName);

	List<ProductDetails> findByProductCategory(String productCategory);

}