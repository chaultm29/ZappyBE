package com.example.springboot.dto;

public class PasswordDTO {

	private String newPassword;
	private String oldPassword;
	
	public PasswordDTO() {
	}

	public PasswordDTO(String newPassword, String oldPassword) {
		super();
		this.newPassword = newPassword;
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	public boolean isEqual() {
		return this.newPassword.equals(this.oldPassword);
	}
}
