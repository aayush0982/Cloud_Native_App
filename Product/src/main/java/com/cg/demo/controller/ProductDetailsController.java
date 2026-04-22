package com.cg.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.demo.entities.ProductDetails;
import com.cg.demo.service.ProductDetailsServiceImpl;

@RestController
public class ProductDetailsController {

	@Autowired
	private ProductDetailsServiceImpl service;

	@GetMapping("/productdetails")
	public List<ProductDetails> getProductDetails() {
		return service.getAllProductDetails();
	}

	@PostMapping("/productdetails")
	public void addProductDetails(@RequestBody ProductDetails productDetails) {
		service.addProductDetails(productDetails);
	}

	@GetMapping("/productdetailsbyname/{productName}")
	public ProductDetails getProductDetailsByName(@PathVariable String productName) {
		return service.getProductDetailsByName(productName);
	}
	
	@GetMapping("/productdetailsids/{productId}")
	public ProductDetails getProductDetailsById(@PathVariable Integer productId) {
		return service.getProductDetailsById(productId);
	}
	
	@GetMapping("/productdetails/{productCategory}")
	public List<ProductDetails> getProductDetailsByCategory(@PathVariable String productCategory) {
		return service.getProductDetailsByCategory(productCategory);
	}
}
