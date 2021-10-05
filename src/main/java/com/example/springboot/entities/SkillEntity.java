package com.example.springboot.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Skill")
public class SkillEntity {
	@Id
	@Column(name = "skill_id")
	private Long id;

	@Column(name = "skill_name")
	private String skillName;

	public SkillEntity() {
		super();
	}

	public SkillEntity(Long id, String skillName) {
		super();
		this.id = id;
		this.skillName = skillName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

}
