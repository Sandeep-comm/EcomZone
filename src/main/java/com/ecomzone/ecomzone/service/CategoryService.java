package com.ecomzone.ecomzone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomzone.ecomzone.graphqlExceptions.CustomGraphqlException;
import com.ecomzone.ecomzone.model.Category;
import com.ecomzone.ecomzone.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> getCategories()
	{
		return categoryRepository.findAll();
	}
	
	public Category getCategory(Long id)
	{
		return Optional.ofNullable(categoryRepository.findById(id).orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
				+ "2. Please provide Valid id "
				+ "3. Invalid  "+id.toString()+" value"))).get();
	}
	
	public Category createCategory(String name)
	{
		Category category = new Category();
		category.setName(name);
		return categoryRepository.save(category);
	}

	public Category updateCategory(Long id, String name)
	{
		Category category = Optional.ofNullable(categoryRepository.findById(id).orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
				+ "2. Please provide Valid id "
				+ "3. Invalid  "+id.toString()+" value"))).get();
		category.setName(name);
		return categoryRepository.save(category);
	}
	
	public Boolean deleteCategory(Long id) {
		Category category = Optional.ofNullable(categoryRepository.findById(id).orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
				+ "2. Please provide Valid id "
				+ "3. Invalid  "+id.toString()+" value"))).get();
		categoryRepository.deleteById(category.getId());
		return true;
	}
}
