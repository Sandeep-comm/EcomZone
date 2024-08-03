package com.ecomzone.ecomzone.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.ecomzone.ecomzone.model.Role;
import com.ecomzone.ecomzone.service.RoleService;

@Controller
public class RoleController {
	
	
	@Autowired
	private RoleService roleService;
	
	@QueryMapping
	public List<Role> getRoles()
	{
		return roleService.getRoles();
	}
	
	@QueryMapping
	public Optional<Role> getRole(@Argument Long id)
	{
		return roleService.getRole(id);
	}

	@MutationMapping
	public Role createRole(@Argument String name) {
		return roleService.createRole(name);
	}
	
	@MutationMapping
	public Role updateRole(@Argument Long id,@Argument String name)
	{
	  return roleService.updateRole(id, name);
	}
	
	@MutationMapping
	public Boolean deleteRole(@Argument Long id)
	{
	   
		return roleService.deleteRole(id);
	}
}
