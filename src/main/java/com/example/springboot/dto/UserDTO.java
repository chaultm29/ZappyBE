package com.example.springboot.dto;

import java.sql.Date;

public class UserDTO {
	private Date dateOfBirth;
	private String email;
	private String fullName;
	private String phone;
	private String avatar;

	public UserDTO() {
	}

	public UserDTO(Date dateOfBirth, String email, String fullName, String phone, String avatar) {
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.fullName = fullName;
		this.phone = phone;
		this.avatar = avatar;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
