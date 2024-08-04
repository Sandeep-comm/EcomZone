package com.ecomzone.ecomzone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomzone.ecomzone.graphqlExceptions.CustomGraphqlException;
import com.ecomzone.ecomzone.model.Product;
import com.ecomzone.ecomzone.model.ProductReview;
import com.ecomzone.ecomzone.model.User;
import com.ecomzone.ecomzone.repository.ProductRepository;
import com.ecomzone.ecomzone.repository.ProductReviewRepository;
import com.ecomzone.ecomzone.repository.UserRepository;

@Service
public class ProductReviewService {
	
	@Autowired
	private ProductReviewRepository productReviewRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<ProductReview> getProductReviews()
	{
		return productReviewRepository.findAll();
	}
	
	public ProductReview getProductReview(Long id)
	{
		ProductReview productReview = Optional.ofNullable(productReviewRepository.findById(id).orElseThrow(
				() -> new CustomGraphqlException(404, "1. No data Found "
						+ "2. Please provide Valid id "
						+ "3. Invalid  "+id.toString()+" value"))).get();
		return productReview;
	}
	
	public ProductReview createProductReview(Long userId,Long productId, Integer rating, String review)
	{
		User user = Optional.ofNullable(userRepository.findById(userId).orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
				+ "2. Please provide Valid id "
				+ "3. Invalid  "+userId.toString()+" value"))).get();
		Product product = Optional.ofNullable(productRepository.findById(productId).
				orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
						+ "2. Please provide Valid id "
						+ "3. Invalid  "+productId.toString()+" value"))).get();
		
		ProductReview productReview = new ProductReview();
		productReview.setProduct(product);
		productReview.setUser(user);
		productReview.setRating(rating);
		productReview.setReview(review);
		return productReviewRepository.save(productReview);
	}
	
	public ProductReview updateProductReview(Long id,Long userId,Long productId, Integer rating, String review)
	{
		ProductReview productReview = Optional.ofNullable(productReviewRepository.findById(id).orElseThrow(
				() -> new CustomGraphqlException(404, "1. No data Found "
						+ "2. Please provide Valid id "
						+ "3. Invalid  "+id.toString()+" value"))).get();
		User user = Optional.ofNullable(userRepository.findById(userId!=null?userId:productReview.getUser().getId()).orElseThrow()).get();
		
		Product product = Optional.ofNullable(productRepository.findById(productId!=null?productId:productReview.getProduct().getId()).orElseThrow()).get();
		if(rating!=null)
		{
			productReview.setRating(rating);
		}
		if(review!=null)
		{
			productReview.setReview(review);
		}
		productReview.setProduct(product);
		productReview.setUser(user);
		
		return productReviewRepository.save(productReview);
	}
	
     public Boolean deleteProductReview(Long id)
     {
    	 ProductReview productReview = Optional.ofNullable(productReviewRepository.findById(id).orElseThrow(
 				() -> new CustomGraphqlException(404, "1. No data Found "
 						+ "2. Please provide Valid id "
 						+ "3. Invalid  "+id.toString()+" value"))).get();
    	 productReviewRepository.deleteById(id);
    	 return true;
     }

}
