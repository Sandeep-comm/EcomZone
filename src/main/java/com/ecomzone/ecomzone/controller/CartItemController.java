package com.ecomzone.ecomzone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.ecomzone.ecomzone.model.CartItem;
import com.ecomzone.ecomzone.service.CartItemService;

@Controller
public class CartItemController {
	
	@Autowired
	private CartItemService cartItemService;
	
	@QueryMapping
	public List<CartItem> getCartItems()
	{
		return cartItemService.getCartItems();
	}
	@QueryMapping
	public CartItem getCartItem(@Argument Long id)
	{
		return cartItemService.getCartItem(id);
	}
	
	@MutationMapping
	public CartItem createCartItem(@Argument Long cartId,@Argument Long productId,@Argument Integer  quantity) {
		return cartItemService.createCartItem(cartId, productId, quantity);
	}
	
	@MutationMapping
	public CartItem updateCartItem(@Argument Long id,@Argument Long cartId,@Argument Long productId,@Argument Integer  quantity) {
		return cartItemService.updateCartItem(id, cartId, productId, quantity);
	}
	@MutationMapping
	public Boolean deleteCartItem(@Argument Long id)
	{
		return cartItemService.deleteCartItem(id);
	}
		

}
