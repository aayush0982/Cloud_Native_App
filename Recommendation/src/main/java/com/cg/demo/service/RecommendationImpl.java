package com.cg.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.demo.entity.Recommendation;
import com.cg.demo.repo.RecommendationRepo;
@Service
public class RecommendationImpl implements RecommendationInterface {
	@Autowired
	private RecommendationRepo recommendationRepo;

	@Override
	public List<Recommendation> getRecommendationByProductId(String productCategory) {
		List<Recommendation> recommendations = recommendationRepo.findByProductCategory(productCategory);
		return recommendations;
	}

	@Override
	public Recommendation addRecommendation(Recommendation recommendation) {
		Recommendation savedRecommendation = recommendationRepo.save(recommendation);
		return savedRecommendation;
	}

}
