package com.ecomzone.ecomzone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomzone.ecomzone.graphqlExceptions.CustomGraphqlException;
import com.ecomzone.ecomzone.model.Role;
import com.ecomzone.ecomzone.repository.RoleRepository;

@Service
public class RoleService {
	
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> getRoles()
	{
		return roleRepository.findAll();
	}
	
	public Optional<Role> getRole(Long id)
	{
		return Optional.ofNullable(roleRepository.findById(id).orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
				+ "2. Please provide Valid id "
				+ "3. Invalid  "+id.toString()+" value")));
		
	}
	
	public Role createRole(String name) {
		
		Role role = new Role();
		role.setName(name);
		return roleRepository.save(role);	
	}
	
	public Role updateRole(Long id, String name)
	{
		Optional<Role> role =Optional.ofNullable(roleRepository.findById(id).orElseThrow(() -> new CustomGraphqlException(404, "1. No data Found "
				+ "2. Please provide Valid id "
				+ "3. Invalid  "+id.toString()+" value")));
		
		role.get().setName(name);
		return roleRepository.save(role.get());
	}
	
	public Boolean deleteRole(Long id)
	{
		Optional<Role> role1= roleRepository.findById(id);
		
		if(role1.isPresent())
		{
		roleRepository.deleteById(id);
		Role role = roleRepository.findById(id).orElse(null);
		return role!=null?false:true;
		}
		else {
			 throw new CustomGraphqlException(404, "1. No data Found "
					+ "2. Please provide Valid id "
					+ "3. Invalid  "+id.toString()+" value");
		}
	}

}
