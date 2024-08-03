package com.ecomzone.ecomzone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomzone.ecomzone.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
