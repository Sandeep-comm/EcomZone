package com.ecomzone.ecomzone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.ecomzone.ecomzone.model.OrderDetail;
import com.ecomzone.ecomzone.service.OrderDetailService;

@Controller
public class OrderDetailController {
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	
	@QueryMapping
	public List<OrderDetail> getOrderDetails()
	{
	  return orderDetailService.getOrderDetails();
	}
	
	@QueryMapping
	public OrderDetail getOrderDetail(@Argument Long id)
	{
	  return orderDetailService.getOrderDetail(id);
	}
	
	@MutationMapping
	public OrderDetail createOrderDetail(@Argument Long orderId,@Argument Long  productId,
			@Argument Integer quantity,@Argument Double price) {
		return orderDetailService.createOrderDetail(orderId, productId, quantity, price);		
	}
	
	@MutationMapping
	public OrderDetail updateOrderDetail(@Argument Long id,@Argument Integer quantity,@Argument Double price) {
		return orderDetailService.updateOrderDetail(id, quantity, price);
		
	}
	@MutationMapping
	public Boolean deleteOrderDetail(@Argument Long id)
	{
		return orderDetailService.deleteOrderDetail(id);
	}
	

}
