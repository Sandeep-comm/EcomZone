package com.ecomzone.ecomzone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.ecomzone.ecomzone.model.Product;
import com.ecomzone.ecomzone.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	@QueryMapping
	public List<Product> getProducts()
	{
		return productService.getProducts();
	}
	
	@QueryMapping
	public Product getProduct(@Argument Long id)
	{
		return productService.getProduct(id);
	}
	
	@MutationMapping
	public Product createProduct(@Argument String name,@Argument String  description,@Argument Double price,@Argument Long categoryId)
	{
		return productService.createProduct(name, description, price, categoryId);
	}
	@MutationMapping
	public Product updateProduct(@Argument Long id,@Argument String name,@Argument String  description,@Argument  Double price,
			@Argument  Long categoryId)
	{
		return productService.updateProduct(id, name, description, price, categoryId);
		
	}
	
	@MutationMapping
	public Boolean deleteProduct(@Argument Long id)
	{
		return productService.deleteProduct(id);
	}
	

}
