package com.cg.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.demo.entities.ProductDetails;
import com.cg.demo.repo.ProductDetailsRepo;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsServiceInterface {
	@Autowired
	ProductDetailsRepo productRepo;

	@Override
	public void addProductDetails(ProductDetails productDetails) {
		// TODO Auto-generated method stub
		productRepo.save(productDetails);

	}

	@Override
	public ProductDetails getProductDetailsByName(String productName) {
		// TODO Auto-generated method stub
		ProductDetails productDetails = productRepo.findByProductName(productName);
		return productDetails;
	}

	@Override
	public List<ProductDetails> getAllProductDetails() {
		// TODO Auto-generated method stub
		List<ProductDetails> list = productRepo.findAll();
		return list;
	}

	@Override
	public ProductDetails getProductDetailsById(Integer productId) {
		// TODO Auto-generated method stub
		Optional<ProductDetails> productDetail = productRepo.findById(productId);
		ProductDetails productDetails = null;
		if (productDetail.isPresent()) {
			productDetails = productDetail.get();
		}
		return productDetails;
	}

	@Override
	public List<ProductDetails> getProductDetailsByCategory(String productCategory) {
		// TODO Auto-generated method stub
		List<ProductDetails> list = productRepo.findByProductCategory(productCategory);
		return list;
	}

}
