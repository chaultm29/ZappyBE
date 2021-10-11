package com.example.springboot.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "questionType")
public class QuestionTypeEntity {
	@Id
	@Column(name = "type_id")
	private Long id;

	@Column(name = "type_name")
	private String typeName;
	
	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	private Set<QuestionEntity> questionEntities;
	
	@ManyToMany(mappedBy = "questionTypeEntities")
	private Set<GameEntity> gameEntities;

	public QuestionTypeEntity() {
	}

	public QuestionTypeEntity(Long id, String typeName) {
		super();
		this.id = id;
		this.typeName = typeName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
