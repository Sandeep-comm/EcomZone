package com.ecomzone.ecomzone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Service;

import com.ecomzone.ecomzone.graphqlExceptions.CustomGraphqlException;
import com.ecomzone.ecomzone.model.Cart;
import com.ecomzone.ecomzone.model.CartItem;
import com.ecomzone.ecomzone.model.Product;
import com.ecomzone.ecomzone.repository.CartItemRepository;
import com.ecomzone.ecomzone.repository.CartRepository;
import com.ecomzone.ecomzone.repository.ProductRepository;

@Service
public class CartItemService {
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<CartItem> getCartItems()
	{
		return cartItemRepository.findAll();
	}
	
	
	public CartItem getCartItem( Long id)
	{
		return Optional.ofNullable(cartItemRepository.findById(id).orElseThrow(
				() -> new CustomGraphqlException(404, "1. No data Found "
						+ "2. Please provide Valid id "
						+ "3. Invalid  "+id.toString()+" value"))).get();
	}
	
	
	public CartItem createCartItem(Long cartId,Long productId,Integer  quantity) {
		Cart cart = Optional.ofNullable(cartRepository.findById(cartId).orElseThrow(
				() -> new CustomGraphqlException(404, "1. No data Found "
						+ "2. Please provide Valid id "
						+ "3. Invalid  "+cartId.toString()+" value"))).get();
		Product product = Optional.ofNullable(productRepository.findById(productId).
				orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
						+ "2. Please provide Valid id "
						+ "3. Invalid  "+productId.toString()+" value"))).get();
		
		CartItem cartItem = new CartItem();
		cartItem.setCart(cart);
		cartItem.setProduct(product);
		cartItem.setQuantity(quantity);
		
		return cartItemRepository.save(cartItem);
	}
	public CartItem updateCartItem(Long id,Long cartId,Long productId,Integer  quantity) {
	CartItem cartItem =	Optional.ofNullable(cartItemRepository.findById(id).orElseThrow(
				() -> new CustomGraphqlException(404, "1. No data Found "
						+ "2. Please provide Valid id "
						+ "3. Invalid  "+id.toString()+" value"))).get();
	Cart cart = Optional.ofNullable(cartRepository.findById(cartId!=null?cartId:cartItem.getCart().getId()).orElseThrow(
			() -> new CustomGraphqlException(404, "1. No data Found "
					+ "2. Please provide Valid id "
					+ "3. Invalid  "+cartId.toString()+" value"))).get();
	Product product = Optional.ofNullable(productRepository.findById(productId!=null?productId:
		cartItem.getProduct().getId()).
			orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
					+ "2. Please provide Valid id "
					+ "3. Invalid  "+productId.toString()+" value"))).get();
	
	
	cartItem.setCart(cart);
	cartItem.setProduct(product);
	if(quantity!=null)
	{
	cartItem.setQuantity(quantity);
	}
	return cartItemRepository.save(cartItem);
	}
	
	public Boolean deleteCartItem(Long id)
	{
		CartItem cartItem =	Optional.ofNullable(cartItemRepository.findById(id).orElseThrow(
				() -> new CustomGraphqlException(404, "1. No data Found "
						+ "2. Please provide Valid id "
						+ "3. Invalid  "+id.toString()+" value"))).get();
		
		cartItemRepository.deleteById(cartItem.getId());
		return true;
	}
	
	

}
