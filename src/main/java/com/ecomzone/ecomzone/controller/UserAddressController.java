package com.ecomzone.ecomzone.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.ecomzone.ecomzone.model.UserAddress;
import com.ecomzone.ecomzone.service.UserAddressService;

@Controller
public class UserAddressController {
	
	@Autowired
	private UserAddressService userAddressService;
	
	@QueryMapping
	public List<UserAddress> getUserAddresses()
	{
	  return userAddressService.getUserAddresses();
	}
	
	@QueryMapping
	public UserAddress  getUserAddress(@Argument Long id)
	{
		
		return userAddressService.getUserAddress(id);
	}
	
	@MutationMapping
	public UserAddress createUserAddress(@Argument Long userId,@Argument String street,@Argument String city,
			@Argument String state,@Argument String zip) {
		
		return userAddressService.createUserAddress(userId, street, city, state, zip);
		
	}
	
	@MutationMapping
	public UserAddress updateUserAddress(@Argument Long id,@Argument Long userId,@Argument String street,@Argument String city,
			@Argument String state,@Argument String zip) {
		
		return userAddressService.updateUserAddress(id, userId, street, city, state, zip);
	
	}
	
	@MutationMapping
	public Boolean deleteUserAddress(@Argument Long id)
	{
	return userAddressService.deleteUserAddress(id);
	}
	
	
	

}
