package com.example.springboot.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Role")
public class RoleEntity {
	@Id
	@Column(name = "role_id")
	private Long id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	private Set<AccountEntity> accountEntity;

	public RoleEntity() {
		super();
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
