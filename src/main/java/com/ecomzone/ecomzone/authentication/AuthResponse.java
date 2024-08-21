package com.ecomzone.ecomzone.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthResponse {
	
	private boolean valid;
    private String message;

}
