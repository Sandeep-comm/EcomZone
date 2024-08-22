package com.ecomzone.ecomzone.authentication;

import java.util.List;

import com.ecomzone.ecomzone.model.Order;
import com.ecomzone.ecomzone.model.User;
import com.ecomzone.ecomzone.model.UserAddress;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthResponse {
	
	private boolean valid;
    private String message;
    private User user;
    

}
