package com.cg.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.demo.entity.Catalogue;
import com.cg.demo.modal.ProductDetails;
import com.cg.demo.modal.ProductInventory;
import com.cg.demo.modal.ProductPricing;
import com.cg.demo.modal.Recommendation;

@RestController
public class CatalogueController {

	@Autowired
	RestTemplate restTemplate;

	@PostMapping("/catalogue/products")
	public String addProduct(@RequestBody Catalogue catalogue) {

		ProductDetails details = new ProductDetails();
		details.setProductId(catalogue.getProductId());
		details.setProductName(catalogue.getProductName());
		details.setProductCategory(catalogue.getProductCategory());

		ProductPricing pricing = new ProductPricing();
		pricing.setProductId(catalogue.getProductId());
		pricing.setProductPrice(catalogue.getProductPrice());

		ProductInventory inventory = new ProductInventory();
		inventory.setProductId(catalogue.getProductId());
		inventory.setProductQuantity(catalogue.getProductStock());

		restTemplate.postForObject("http://Product/productdetails", details, ProductDetails.class);

		restTemplate.postForObject("http://Pricing/price", pricing, ProductPricing.class);

		restTemplate.postForObject("http://Inventory/inventory", inventory, ProductInventory.class);

		restTemplate.postForObject("http://Recommendation/recommendations/" + catalogue.getProductCategory(), null,
				Recommendation.class);

		return "Product saved in all microservices and recommendation updated!";

	}

	@GetMapping("/catalogue/products")
	public List<Catalogue> getAllProducts() {

		ProductDetails[] detailsList = restTemplate.getForObject("http://Product/productdetails",
				ProductDetails[].class);

		ProductPricing[] pricingList = restTemplate.getForObject("http://Pricing/price", ProductPricing[].class);

		ProductInventory[] inventoryList = restTemplate.getForObject("http://Inventory/inventory",
				ProductInventory[].class);

		List<Catalogue> result = new ArrayList<>();

		for (ProductDetails d : detailsList) {

			Catalogue c = new Catalogue();
			c.setProductId(d.getProductId());
			c.setProductName(d.getProductName());
			c.setProductCategory(d.getProductCategory());

			for (ProductPricing p : pricingList) {
				if (p.getProductId().equals(d.getProductId())) {
					c.setProductPrice(p.getProductPrice());
				}
			}

			for (ProductInventory i : inventoryList) {
				if (i.getProductId().equals(d.getProductId())) {
					c.setProductStock(i.getProductQuantity());
				}
			}

			result.add(c);
		}

		return result;
	}

	@GetMapping("/catalogue/productnames/{name}")
	public Catalogue getByName(@PathVariable String name) {

		ProductDetails details = restTemplate.getForObject("http://Product/productdetailsbyname/" + name,
				ProductDetails.class);

		Double pricing = restTemplate.getForObject("http://Pricing/price/" + details.getProductId(), Double.class);

		Integer inventory = restTemplate.getForObject("http://Inventory/inventory/" + details.getProductId(),
				Integer.class);

		Catalogue c = new Catalogue();
		c.setProductId(details.getProductId());
		c.setProductName(details.getProductName());
		c.setProductCategory(details.getProductCategory());
		c.setProductPrice(pricing);
		c.setProductStock(inventory);

		return c;
	}

	@GetMapping("/catalogue/productids/{id}")
	public Catalogue getById(@PathVariable Integer id) {

		ProductDetails details = restTemplate.getForObject("http://Product/productdetailsids/" + id,
				ProductDetails.class);

		Double pricing = restTemplate.getForObject("http://Pricing/price/" + id, Double.class);

		Integer inventory = restTemplate.getForObject("http://Inventory/inventory/" + id, Integer.class);

		Catalogue c = new Catalogue();
		c.setProductId(details.getProductId());
		c.setProductName(details.getProductName());
		c.setProductCategory(details.getProductCategory());
		c.setProductPrice(pricing);
		c.setProductStock(inventory);

		return c;
	}

	@GetMapping("/catalogue/productcategories/{category}")
	public List<Catalogue> getByCategory(@PathVariable String category) {

		ProductDetails[] detailsList = restTemplate.getForObject("http://Product/productdetails/" + category,
				ProductDetails[].class);

		List<Catalogue> result = new ArrayList<>();

		for (ProductDetails d : detailsList) {

			Double pricing = restTemplate.getForObject("http://Pricing/price/" + d.getProductId(), Double.class);

			Integer inventory = restTemplate.getForObject("http://Inventory/inventory/" + d.getProductId(),
					Integer.class);

			Catalogue c = new Catalogue();
			c.setProductId(d.getProductId());
			c.setProductName(d.getProductName());
			c.setProductCategory(d.getProductCategory());
			c.setProductPrice(pricing);
			c.setProductStock(inventory);

			result.add(c);
		}

		return result;
	}

}
