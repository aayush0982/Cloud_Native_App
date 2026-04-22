package com.cg.demo.service;

import java.util.List;

import com.cg.demo.entity.Recommendation;

public interface RecommendationInterface {
	public List<Recommendation> getRecommendationByProductId(String productCategory);

	public Recommendation addRecommendation(Recommendation recommendation);

}
