package com.ecomzone.ecomzone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomzone.ecomzone.graphqlExceptions.CustomGraphqlException;
import com.ecomzone.ecomzone.model.User;
import com.ecomzone.ecomzone.model.UserAddress;
import com.ecomzone.ecomzone.repository.UserAddressRepository;
import com.ecomzone.ecomzone.repository.UserRepository;

@Service
public class UserAddressService {
	
	@Autowired
	private UserAddressRepository userAddressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	
	public List<UserAddress> getUserAddresses()
	{
	  return userAddressRepository.findAll();
	}
	
	public UserAddress  getUserAddress(Long id)
	{
		return Optional.ofNullable(userAddressRepository.findById(id).orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
				+ "2. Please provide Valid id "
				+ "3. Invalid  "+id.toString()+" value"))).get();
			
	}
	
	public UserAddress createUserAddress(Long userId,String street,String city,String state,String zip) {
		
		User user = Optional.ofNullable(userRepository.findById(userId).orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
				+ "2. Please provide Valid id "
				+ "3. Invalid  "+userId.toString()+" value"))).get();
		UserAddress userAddress = new UserAddress();
		userAddress.setCity(city);
		userAddress.setState(state);
		userAddress.setZip(zip);
		userAddress.setStreet(street);
		userAddress.setUser(user);
		return userAddressRepository.save(userAddress);	
		
	}
	
	public UserAddress updateUserAddress(Long id,Long userId,String street,String city,String state,String zip) {
		UserAddress userAddress =Optional.ofNullable(userAddressRepository.findById(id).orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
				+ "2. Please provide Valid id "
				+ "3. Invalid  "+id.toString()+" value"))).get();
		User user = Optional.ofNullable(userRepository.findById(userId!=null?userId:
			userAddress.getUser().getId()).orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
				+ "2. Please provide Valid id "
				+ "3. Invalid  "+userId.toString()+" value"))).get();
		if(street!=null)
		{
			userAddress.setStreet(street);
		}
		if(city!=null)
		{
			userAddress.setCity(city);
		}
		if(state!=null)
		{
			userAddress.setState(state);
		}
		if(zip!=null)
		{
			userAddress.setZip(zip);
		}
		
		return userAddressRepository.save(userAddress);
		
	}
	
	public Boolean deleteUserAddress(Long id)
	{
		UserAddress userAddress =Optional.ofNullable(userAddressRepository.findById(id).orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
				+ "2. Please provide Valid id "
				+ "3. Invalid  "+id.toString()+" value"))).get();
		userAddressRepository.deleteById(userAddress.getId());
		return true;
	}

}
