package com.cg.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.demo.entities.ProductInventory;
import com.cg.demo.repo.ProductInventoryServiceImpl;

@RestController
public class InventoryController {

	@Autowired
	private ProductInventoryServiceImpl inventoryService;

	@GetMapping("/inventory")
	public List<ProductInventory> getInventory() {
		return inventoryService.getAllProducts();
	}

	@PostMapping("/inventory")
	public ProductInventory addProduct(@RequestBody ProductInventory product) {
		return inventoryService.addProduct(product);
	}

	@GetMapping("/inventory/{productId}")
	public Integer getProductStockById(@PathVariable Integer productId) {
		return inventoryService.getProductStockById(productId);
	}

}
