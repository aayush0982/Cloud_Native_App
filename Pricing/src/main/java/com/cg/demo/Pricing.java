package com.cg.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Pricing {

	public static void main(String[] args) {
		SpringApplication.run(Pricing.class, args);
		System.out.println("Product Pricing Service is running...");
	}

}
