package com.ecomzone.ecomzone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ecomzone.ecomzone.graphqlExceptions.CustomGraphqlException;
import com.ecomzone.ecomzone.model.Category;
import com.ecomzone.ecomzone.model.Product;
import com.ecomzone.ecomzone.repository.CategoryRepository;
import com.ecomzone.ecomzone.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Product> getProducts()
	{
		return productRepository.findAll();
	}
	
	public Product getProduct(Long id)
	{
		Product product = Optional.ofNullable(productRepository.findById(id).
				orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
						+ "2. Please provide Valid id "
						+ "3. Invalid  "+id.toString()+" value"))).get();
		return product;
	}
	
	public Product createProduct(String name,String  description,Double price,Long categoryId)
	{
		Category category=Optional.ofNullable(categoryRepository.findById(categoryId).orElseThrow(
				() -> new CustomGraphqlException(404, "1.No data Found "
        				+ "2.Please provide Valid roleId "
        				+ "3.Invalid  "+categoryId.toString()+" value"))).get();
		Product product = new Product();
		product.setName(name);
		product.setCategory(category);
		product.setDescription(description);
		product.setPrice(price);
		return productRepository.save(product);

	}
	
	public Product updateProduct(Long id, String name,String  description,Double price,Long categoryId)
	{
		Product product = Optional.ofNullable(productRepository.findById(id).
				orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
						+ "2. Please provide Valid id "
						+ "3. Invalid  "+id.toString()+" value"))).get();
		Category category=Optional.ofNullable(categoryRepository.findById(categoryId!=null?categoryId:product.getCategory().getId()).orElseThrow()).get();
		
		if(name!=null)
		{
			product.setName(name);
		}
		if(description!=null)
		{
			product.setDescription(description);
		}
		if(price!=null)
		{
			product.setPrice(price);
		}
		product.setCategory(category);
		return productRepository.save(product);
	}
	
	public Boolean deleteProduct(Long id)
	{
		Product product = Optional.ofNullable(productRepository.findById(id).
				orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
						+ "2. Please provide Valid id "
						+ "3. Invalid  "+id.toString()+" value"))).get();
		productRepository.deleteById(product.getId());
		return true;
	}

}
