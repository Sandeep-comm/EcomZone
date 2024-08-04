package com.ecomzone.ecomzone.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomzone.ecomzone.graphqlExceptions.CustomGraphqlException;
import com.ecomzone.ecomzone.model.Order;
import com.ecomzone.ecomzone.model.User;
import com.ecomzone.ecomzone.model.UserAddress;
import com.ecomzone.ecomzone.repository.OrderRepository;
import com.ecomzone.ecomzone.repository.UserAddressRepository;
import com.ecomzone.ecomzone.repository.UserRepository;

@Service
public class OrderService {

	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserAddressRepository userAddressRepository;
	
	public List<Order> getOrders()
	{
		return orderRepository.findAll();
	}
	
	public Order getOrder(Long id)
	{
		return Optional.ofNullable(orderRepository.findById(id).orElseThrow(
				() -> new CustomGraphqlException(404, "1. No data Found "
						+ "2. Please provide Valid id "
						+ "3. Invalid  "+id.toString()+" value"))).get();
	}
	
	public Order createOrder(Long userId,Long  addressId,Double totalPrice,String  orderDate) {
		
		User user =Optional.ofNullable(userRepository.findById(userId).orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
				+ "2. Please provide Valid id "
				+ "3. Invalid  "+userId.toString()+" value"))).get(); 
		UserAddress userAddress =Optional.ofNullable(userAddressRepository.findById(addressId).orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
				+ "2. Please provide Valid id "
				+ "3. Invalid  "+addressId.toString()+" value"))).get();
		Order order = new Order();
		order.setAddress(userAddress);
		order.setUser(user);
		order.setTotalPrice(totalPrice);
		if(orderDate!=null)
		{
			order.setOrderDate(LocalDateTime.parse(orderDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		}else {
			order.setOrderDate(LocalDateTime.now());
		}
		return orderRepository.save(order);
		
	}
	
	public Order updateOrder(Long id,Long userId,Long  addressId,Double totalPrice,String  orderDate) {
	Order order=	Optional.ofNullable(orderRepository.findById(id).orElseThrow(
				() -> new CustomGraphqlException(404, "1. No data Found "
						+ "2. Please provide Valid id "
						+ "3. Invalid  "+id.toString()+" value"))).get();
	User user =Optional.ofNullable(userRepository.findById(userId!=null?userId:order.getUser().getId()).orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
			+ "2. Please provide Valid id "
			+ "3. Invalid  "+userId.toString()+" value"))).get(); 
	UserAddress userAddress =Optional.ofNullable(userAddressRepository.findById(addressId!=null
			?addressId:order.getAddress().getId()).orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
			+ "2. Please provide Valid id "
			+ "3. Invalid  "+addressId.toString()+" value"))).get();
	order.setAddress(userAddress);
	order.setUser(user);
	if(totalPrice!=null)
	{
	order.setTotalPrice(totalPrice);
	}
	if(orderDate!=null)
	{
		order.setOrderDate(LocalDateTime.parse(orderDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	}
	return orderRepository.save(order);
		
	}
	
	public Boolean deleteOrder(Long id)
	{
		Order order=	Optional.ofNullable(orderRepository.findById(id).orElseThrow(
				() -> new CustomGraphqlException(404, "1. No data Found "
						+ "2. Please provide Valid id "
						+ "3. Invalid  "+id.toString()+" value"))).get();
		orderRepository.deleteById(order.getId());
		return true;
	}
}
