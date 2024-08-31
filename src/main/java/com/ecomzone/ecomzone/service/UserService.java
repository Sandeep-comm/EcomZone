package com.ecomzone.ecomzone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomzone.ecomzone.authentication.AuthResponse;
import com.ecomzone.ecomzone.graphqlExceptions.CustomGraphqlException;
import com.ecomzone.ecomzone.model.Role;
import com.ecomzone.ecomzone.model.User;
import com.ecomzone.ecomzone.repository.RoleRepository;
import com.ecomzone.ecomzone.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<User> getUsers()
	{
		return userRepository.findAll();
	}
	
	public User getUser(Long id)
	{
		
		return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
				+ "2. Please provide Valid id "
				+ "3. Invalid  "+id.toString()+" value"))).get();
			
	}
	
	
    public User  createUser(String username, String password, String email, Integer roleId) {
    	
        
       
        Optional<Role> role = Optional.ofNullable(roleRepository.findById((long)roleId).orElseThrow(
        		() -> new CustomGraphqlException(404, "1.No data Found "
        				+ "2.Please provide Valid roleId "
        				+ "3.Invalid  "+roleId.toString()+" value"))); 
       
      User user = new User();
      user.setUsername(username);
      user.setPassword(password);
      user.setEmail(email);
      user.setRole(role.get());
      System.out.println(user);      
      
        // Save the user in the database   
       return userRepository.save(user);
         
    }
    
    public User updateUser(Long id,String username, String password, String email, Long roleId)
    {
    	User  user = Optional.ofNullable(userRepository.findById(id).orElseThrow(
        		() -> new CustomGraphqlException(404, "1.No data Found "
        				+ "2.Please provide Valid id "
        				+ "3.Invalid  "+id.toString()+" value"))).get(); 
    	Role role = Optional.ofNullable(roleRepository.findById(roleId!=null?roleId:user.getRole().getId()).orElseThrow(
        		() -> new CustomGraphqlException(404, "1.No data Found "
        				+ "2.Please provide Valid roleId "
        				+ "3.Invalid  "+roleId.toString()+" value"))).get();
       
    	
    		
    	if (username != null) {
    		user.setUsername(username);
           
        }
        
        if (password != null) {
            user.setPassword(password);
        }
        
        if (email != null) {
            user.setEmail(email);
        }
    	user.setRole(role);
    	
    	User userUpdated = userRepository.save(user);
    	
    	return userUpdated;
    }
    
    public Boolean deleteUser(Long id)
    {
    	if(userRepository.findById(id).isPresent()) {
    	userRepository.deleteById(id);
    	User user = userRepository.findById(id).orElse(null);
    	Boolean res = user!=null? false:true;
    	return res;
    	}
    	else {
    		 throw new CustomGraphqlException(404, "1. No data Found "
 					+ "2. Please provide Valid id "
 					+ "3. Invalid  "+id.toString()+" value");
    	}
    }
    
    public AuthResponse validateUser(String username,String password)
    {
    	
    	User user = userRepository.findByUsername(username);
    	
    	if (user != null && user.getPassword().equals(password)) {
            return new AuthResponse(true, "Login successful",user);
        } else {
            return new AuthResponse(false, "Invalid credentials",null);
        }
    	
    }

	
	

}
