package com.example.springboot.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "Account")
public class AccountEntity {
	@Id
	@Column(name = "acccount_id")
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	@NotNull
	private String password;
	

	@Column(name = "enabled")
	private Boolean enabled;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleEntity roleEntity;

	public AccountEntity() {
		super();
	}

	public AccountEntity(Long id, String username, String password, Boolean enabled) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}
