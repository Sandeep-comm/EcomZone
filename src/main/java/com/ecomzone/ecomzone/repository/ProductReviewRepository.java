package com.ecomzone.ecomzone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomzone.ecomzone.model.ProductReview;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long>{

}
