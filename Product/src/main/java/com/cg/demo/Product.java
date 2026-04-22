package com.cg.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class Product {

	public static void main(String[] args) {
		SpringApplication.run(Product.class, args);
		System.out.println("Product Details Application Started");
	}

}
