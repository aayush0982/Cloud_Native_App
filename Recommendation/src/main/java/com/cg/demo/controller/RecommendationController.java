package com.cg.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.demo.entity.Recommendation;
import com.cg.demo.modal.Catalogue;
import com.cg.demo.service.RecommendationImpl;

@RestController
public class RecommendationController {

	@Autowired
	private RecommendationImpl recommendationImpl;

	@Autowired
	RestTemplate restTemplate;

	@PostMapping("/recommendations/{category}")
	public Recommendation addRecommendation(@PathVariable String category) {

		Catalogue[] products = restTemplate
				.getForObject("http://ProductCatalogue/catalogue/productcategories/" + category, Catalogue[].class);

		List<Integer> productIds = new ArrayList<>();

		if (products != null) {
			for (Catalogue c : products) {
				productIds.add(c.getProductId());
			}
		}

		Recommendation recommendation = new Recommendation();
		recommendation.setProductCategory(category);
		recommendation.setProductIds(productIds);

		return recommendationImpl.addRecommendation(recommendation);
	}

	@GetMapping("/recommendations/{category}")
	public List<Recommendation> getRecommendation(@PathVariable String category) {
		return recommendationImpl.getRecommendationByProductId(category);
	}
}
