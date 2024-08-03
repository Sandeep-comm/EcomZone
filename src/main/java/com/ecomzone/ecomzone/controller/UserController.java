package com.ecomzone.ecomzone.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.ecomzone.ecomzone.model.User;
import com.ecomzone.ecomzone.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@QueryMapping
	public String hello()
	{
		return "Welcome Graphql";
	}
	
	
	@QueryMapping
	public List<User> getUsers()
	{
		return userService.getUsers();
	}
	
	@QueryMapping
	public Optional<User> getUser(@Argument Long id)
	{
		return userService.getUser(id);
	}
	@MutationMapping
    public User createUser(@Argument String username, @Argument String password,
                           @Argument String email, @Argument Long roleId) {
        return userService.createUser(username, password, email, roleId);
    }
	
	@MutationMapping
	public User updateUser( @Argument Long id,@Argument String username,@Argument String password,@Argument String email, 
			@Argument Long roleId) {
		return userService.updateUser(id, username, password, email, roleId);
		
	}
	
	 @MutationMapping
	 public Boolean deleteUser(@Argument Long id)
	    {
	    return userService.deleteUser(id);
	    }

}
