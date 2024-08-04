package com.ecomzone.ecomzone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomzone.ecomzone.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

}
