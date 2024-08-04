package com.ecomzone.ecomzone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.ecomzone.ecomzone.model.Category;
import com.ecomzone.ecomzone.service.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@QueryMapping
	public List<Category> getCategories()
	{
		return categoryService.getCategories();
	}
	
	@QueryMapping
	public Category getCategory(@Argument Long id)
	{
		return categoryService.getCategory(id);
	}
	
	@MutationMapping
	public Category createCategory(@Argument String name)
	{
		return categoryService.createCategory(name);
	}
	
	@MutationMapping
	public Category updateCategory(@Argument Long id,@Argument String name)
	{
	return categoryService.updateCategory(id, name);
	}
	
	@MutationMapping
	public Boolean deleteCategory(@Argument Long id) {
		return categoryService.deleteCategory(id);
	
	}

}
