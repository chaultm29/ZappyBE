package com.example.springboot.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "skill")
public class SkillEntity {
	@Id
	@Column(name = "skill_id")
	private Long id;

	@Column(name = "skill_name")
	private String skillName;
	
	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	private Set<QuestionEntity> questionEntities;

	public SkillEntity() {
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
