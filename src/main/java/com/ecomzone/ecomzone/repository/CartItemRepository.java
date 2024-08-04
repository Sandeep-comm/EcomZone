package com.ecomzone.ecomzone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomzone.ecomzone.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
