package com.ecomzone.ecomzone.graphqlExceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomGraphqlException extends RuntimeException{
	
	 private   int statusCode;
	 private String message;
	 
	 public CustomGraphqlException(int statusCode, String message) {
	        
	        this.statusCode = statusCode;
	        this.message=message;
	    }
	 
	    

}
