package com.example.springboot.dto;

public class RoleDTO {
	private Long id;
	private String name;
	
	public RoleDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public RoleDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
