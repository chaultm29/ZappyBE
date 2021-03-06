package com.example.springboot.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "roleEntity", cascade = CascadeType.ALL)
	private Set<AccountEntity> accountEntity;

	@OneToMany(mappedBy = "roleEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<RoleDetailsEntity> roleDetailsEntities;
	
	public RoleEntity() {
		super();
	}

	public Set<RoleDetailsEntity> getRoleDetailsEntities() {
		return roleDetailsEntities;
	}

	public void setRoleDetailsEntities(Set<RoleDetailsEntity> roleDetailsEntities) {
		this.roleDetailsEntities = roleDetailsEntities;
	}

	public RoleEntity(Long id, String name) {
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
