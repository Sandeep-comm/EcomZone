package com.ecomzone.ecomzone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomzone.ecomzone.model.UserAddress;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

}
