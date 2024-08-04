package com.ecomzone.ecomzone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.ecomzone.ecomzone.model.Cart;
import com.ecomzone.ecomzone.service.CartService;

@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@QueryMapping
	public List<Cart> getCarts()
	{
		return cartService.getCarts();
	}
	
	@QueryMapping
	public Cart getCart(@Argument Long id)
	{
		return cartService.getCart(id);
	}
	
	@MutationMapping
	public Cart createCart(@Argument Long userId)
	{
		return cartService.createCart(userId);
	}
	
	@MutationMapping
	public Boolean deleteCart(@Argument Long id)
	{
		return cartService.deleteCart(id);
	}

}
