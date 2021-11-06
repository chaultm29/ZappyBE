package com.example.springboot.dto;

import java.util.Date;

public class AccountDTO {
	private Long id;
	private String username;
	private String password;
	private Date dateOfBirth;
	private String email;
	private String fullName;
	private String phone;
	private RoleDTO roleDTO;
	private String avatar;

	public AccountDTO() {
		super();
	}

	public AccountDTO(Long id, String username, String password, Date dateOfBirth, String email, String fullName, String phone,
			RoleDTO roleDTO, String avatar) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.fullName = fullName;
		this.phone = phone;
		this.roleDTO = roleDTO;
		this.avatar = avatar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public RoleDTO getRoleDTO() {
		return roleDTO;
	}

	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
