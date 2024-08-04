package com.ecomzone.ecomzone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.ecomzone.ecomzone.model.Order;
import com.ecomzone.ecomzone.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@QueryMapping
	public List<Order> getOrders()
	{
		return orderService.getOrders();
	}

	@QueryMapping
	public Order getOrder(@Argument Long id)
	{
	  return orderService.getOrder(id);
	}
	
	@MutationMapping
	public Order createOrder(@Argument Long userId,@Argument Long  addressId,@Argument  Double totalPrice,
			@Argument  String  orderDate) {
		return orderService.createOrder(userId, addressId, totalPrice, orderDate);
	}
	@MutationMapping
	public Order updateOrder(@Argument Long id,@Argument Long userId,@Argument Long  addressId,@Argument  Double totalPrice,
			@Argument  String  orderDate) {
		return orderService.updateOrder(id,userId, addressId, totalPrice, orderDate);
	}
	
	@MutationMapping
	public Boolean deleteOrder(@Argument Long id)
	{
	  return orderService.deleteOrder(id);
	}
}
