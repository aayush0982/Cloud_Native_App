package com.cg.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.demo.entities.ProductInventory;
import com.cg.demo.service.ProductInventoryServiceInterface;

@Service
public class ProductInventoryServiceImpl implements ProductInventoryServiceInterface {

	@Autowired
	private ProductInventoryRepo inventoryRepo;

	@Override
	public List<ProductInventory> getAllProducts() {
		List<ProductInventory> list = inventoryRepo.findAll();
		return list;
	}

	@Override
	public ProductInventory addProduct(ProductInventory product) {
		inventoryRepo.save(product);
		return product;
	}

	@Override
	public Integer getProductStockById(Integer productId) {
		Optional<ProductInventory> product = inventoryRepo.findById(productId);
		int stock = 0;
		if (product.isPresent()) {
			stock = product.get().getProductQuantity();
		}
		return stock;
	}

}
