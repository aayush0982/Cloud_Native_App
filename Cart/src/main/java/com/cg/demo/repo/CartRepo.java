package com.cg.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.demo.entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer>{

}
