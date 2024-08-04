package com.ecomzone.ecomzone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomzone.ecomzone.graphqlExceptions.CustomGraphqlException;
import com.ecomzone.ecomzone.model.Order;
import com.ecomzone.ecomzone.model.OrderDetail;
import com.ecomzone.ecomzone.model.Product;
import com.ecomzone.ecomzone.repository.OrderDetailRepository;
import com.ecomzone.ecomzone.repository.OrderRepository;
import com.ecomzone.ecomzone.repository.ProductRepository;

@Service
public class OrderDetailService {
	
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<OrderDetail> getOrderDetails()
	{
		return orderDetailRepository.findAll();
	}
	
	public OrderDetail getOrderDetail(Long id)
	{
		return Optional.ofNullable(orderDetailRepository.findById(id).orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
				+ "2. Please provide Valid id "
				+ "3. Invalid  "+id.toString()+" value"))).get();
	}
	
	public OrderDetail createOrderDetail(Long orderId,Long  productId,Integer quantity,Double price) {
		Order order=	Optional.ofNullable(orderRepository.findById(orderId).orElseThrow(
				() -> new CustomGraphqlException(404, "1. No data Found "
						+ "2. Please provide Valid id "
						+ "3. Invalid  "+orderId.toString()+" value"))).get();
		Product product = Optional.ofNullable(productRepository.findById(productId).
				orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
						+ "2. Please provide Valid id "
						+ "3. Invalid  "+productId.toString()+" value"))).get();
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrder(order);
		orderDetail.setProduct(product);
		orderDetail.setPrice(price);
		orderDetail.setQuantity(quantity);
		return orderDetailRepository.save(orderDetail);
	}
	
	public OrderDetail updateOrderDetail(Long id, Integer quantity,Double price) {
		OrderDetail orderDetail =Optional.ofNullable(orderDetailRepository.findById(id).orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
				+ "2. Please provide Valid id "
				+ "3. Invalid  "+id.toString()+" value"))).get();
		if(price!=null)
		{
		orderDetail.setPrice(price);
		}
		if(quantity!=null)
		{
		orderDetail.setQuantity(quantity);
		}
		
		return orderDetailRepository.save(orderDetail);
		
	}
	public Boolean deleteOrderDetail(Long id)
	{
		OrderDetail orderDetail =Optional.ofNullable(orderDetailRepository.findById(id).orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
				+ "2. Please provide Valid id "
				+ "3. Invalid  "+id.toString()+" value"))).get();
		orderDetailRepository.deleteById(orderDetail.getId());
		return true;
		
		
	}


}
