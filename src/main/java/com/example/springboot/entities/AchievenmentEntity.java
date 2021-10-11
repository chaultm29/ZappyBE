package com.example.springboot.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "achievements")
public class AchievenmentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "desciption")
	private String desciption;

	@Column(name = "achieve_condition")
	private String condition;
	
	@ManyToMany(mappedBy = "achievenmentEntities")
	private Set<UserEntity> userEntities;
	
	public AchievenmentEntity() {
	}

	public AchievenmentEntity(Long id, String name, String desciption, String condition) {
		super();
		this.id = id;
		this.name = name;
		this.desciption = desciption;
		this.condition = condition;
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

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

}
