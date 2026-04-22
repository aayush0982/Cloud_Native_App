package com.cg.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.demo.entities.ProductInventory;

public interface ProductInventoryRepo extends JpaRepository<ProductInventory, Integer> {

}
