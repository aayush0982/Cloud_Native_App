package com.cg.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Inventory {

	public static void main(String[] args) {
		SpringApplication.run(Inventory.class, args);
		System.out.println("Inventory Service is running...");
	}

}
