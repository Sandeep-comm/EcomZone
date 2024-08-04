package com.ecomzone.ecomzone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomzone.ecomzone.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	

}
