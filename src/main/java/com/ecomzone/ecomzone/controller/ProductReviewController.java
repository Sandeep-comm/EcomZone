package com.ecomzone.ecomzone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.ecomzone.ecomzone.model.ProductReview;
import com.ecomzone.ecomzone.service.ProductReviewService;

@Controller
public class ProductReviewController {
	
	@Autowired
	private ProductReviewService productReviewService;
	
	@QueryMapping
	public List<ProductReview> getProductReviews()
	{
		return productReviewService.getProductReviews();
	}
	
	@QueryMapping
	public ProductReview getProductReview(@Argument Long id)
	{
		return productReviewService.getProductReview(id);
	}
	
	@MutationMapping
	public ProductReview createProductReview(@Argument Long userId,@Argument Long productId,@Argument Integer rating,@Argument String review)
	{
	  return productReviewService.createProductReview(userId, productId, rating, review);
	}
	
	@MutationMapping
	public ProductReview updateProductReview(@Argument Long id,@Argument Long userId,@Argument Long productId, 
			@Argument Integer rating, @Argument String review)
	{
		return productReviewService.updateProductReview(id, userId, productId, rating, review);
	}
	
	@MutationMapping
	public Boolean deleteProductReview(@Argument Long id) {
		return productReviewService.deleteProductReview(id);
		
	}

}
