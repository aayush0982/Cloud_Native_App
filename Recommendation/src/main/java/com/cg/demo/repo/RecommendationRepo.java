package com.cg.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.demo.entity.Recommendation;

public interface RecommendationRepo extends JpaRepository<Recommendation, String>{

	List<Recommendation> findByProductCategory(String productCategory);

}
