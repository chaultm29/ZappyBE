package com.example.springboot.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "role_details")
public class RoleDetailsEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "link")
	private String link;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleEntity roleEntity;

	public RoleDetailsEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public RoleDetailsEntity(Long id, String link, RoleEntity roleEntity) {
		super();
		this.id = id;
		this.link = link;
		this.roleEntity = roleEntity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public RoleEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(RoleEntity roleEntity) {
		this.roleEntity = roleEntity;
	}
	
}
