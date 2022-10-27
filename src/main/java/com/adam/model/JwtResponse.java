package com.adam.model;

import lombok.Getter;

@Getter
public class JwtResponse {
	private String jwtToken;
	
	     public JwtResponse (String jwtToken) {
	    	 this.jwtToken=jwtToken;
	     }
}
