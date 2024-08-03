package com.ecomzone.ecomzone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public Optional<User> getUser(Long id)
	{
		
		return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
				+ "2. Please provide Valid id "
				+ "3. Invalid  "+id.toString()+" value")));
			
	}
	
	
    public User  createUser(String username, String password, String email, Long roleId) {
        
        
        Optional<Role> role = Optional.ofNullable(roleRepository.findById(roleId).orElseThrow(
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
    	Optional<User>  user = Optional.ofNullable(userRepository.findById(id).orElseThrow(
        		() -> new CustomGraphqlException(404, "1.No data Found "
        				+ "2.Please provide Valid id "
        				+ "3.Invalid  "+id.toString()+" value"))); 
    	Optional<Role> role = roleId!=null?Optional.ofNullable(roleRepository.findById(roleId).orElseThrow(
        		() -> new CustomGraphqlException(404, "1.No data Found "
        				+ "2.Please provide Valid roleId "
        				+ "3.Invalid  "+roleId.toString()+" value"))):
        					
        					Optional.ofNullable(roleRepository.findById(user.get().getRole().getId()).orElseThrow(
        			        		() -> new CustomGraphqlException(404, "1.No data Found "
        			        				+ "2.Please provide Valid roleId "
        			        				+ "3.Invalid  "+roleId.toString()+" value")));
       
    	
    		
    	if (username != null) {
    		user.get().setUsername(username);
           
        }
        
        if (password != null) {
            user.get().setPassword(password);
        }
        
        if (email != null) {
            user.get().setEmail(email);
        }
    	user.get().setRole(role.get());
    	
    	User userUpdated = userRepository.save(user.get());
    	
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

	

}
