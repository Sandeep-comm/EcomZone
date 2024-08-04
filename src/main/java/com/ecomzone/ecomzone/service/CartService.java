package com.ecomzone.ecomzone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomzone.ecomzone.graphqlExceptions.CustomGraphqlException;
import com.ecomzone.ecomzone.model.Cart;
import com.ecomzone.ecomzone.model.User;
import com.ecomzone.ecomzone.repository.CartRepository;
import com.ecomzone.ecomzone.repository.UserRepository;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Cart> getCarts()
	{
		return cartRepository.findAll();
	}
	
	public Cart getCart(Long id)
	{
		return Optional.ofNullable(cartRepository.findById(id).orElseThrow(
				() -> new CustomGraphqlException(404, "1. No data Found "
						+ "2. Please provide Valid id "
						+ "3. Invalid  "+id.toString()+" value"))).get();
	}
	
	public Cart createCart(Long userId)
	{
		User user = Optional.ofNullable(userRepository.findById(userId).orElseThrow(
				() -> new CustomGraphqlException(404, "1. No data Found "
						+ "2. Please provide Valid id "
						+ "3. Invalid  "+userId.toString()+" value"))).get();
		Cart cart = new Cart();
		cart.setUser(user);
		return cartRepository.save(cart);
	}
	
	public Boolean deleteCart(Long id)
	{
		Cart cart = Optional.ofNullable(cartRepository.findById(id).orElseThrow(
				() -> new CustomGraphqlException(404, "1. No data Found "
						+ "2. Please provide Valid id "
						+ "3. Invalid  "+id.toString()+" value"))).get();
		cartRepository.deleteById(cart.getId());
		return true;
	}
	
	

}
