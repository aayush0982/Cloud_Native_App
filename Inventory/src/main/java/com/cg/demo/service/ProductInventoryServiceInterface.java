package com.cg.demo.service;

import java.util.List;

import com.cg.demo.entities.ProductInventory;

public interface ProductInventoryServiceInterface {
	public List<ProductInventory> getAllProducts();

	public Integer getProductStockById(Integer productId);

	public ProductInventory addProduct(ProductInventory product);
}
