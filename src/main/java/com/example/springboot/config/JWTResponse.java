package com.example.springboot.config;

import java.io.Serializable;

public class JWTResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String jwttoken;
	private String username;
	private String rolename;
	
	public JWTResponse() {
		// TODO Auto-generated constructor stub
	}
	
	
	public JWTResponse(String jwttoken, String username, String rolename) {
		super();
		this.jwttoken = jwttoken;
		this.username = username;
		this.rolename = rolename;
	}


	public String getToken() {
		return this.jwttoken;
	}


	public String getUsername() {
		return username;
	}


	public String getRolename() {
		return rolename;
	}
}
