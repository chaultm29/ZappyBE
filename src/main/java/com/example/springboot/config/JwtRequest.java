package com.example.springboot.config;

import java.io.Serializable;

public class JwtRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2435860062477365778L;
	private String username;
	private String password;
	
	public JwtRequest() {
		// TODO Auto-generated constructor stub
	}

	public JwtRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
