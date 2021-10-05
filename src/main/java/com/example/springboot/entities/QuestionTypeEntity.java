package com.example.springboot.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QuestionType")
public class QuestionTypeEntity {
	@Id
	@Column(name = "type_id")
	private Long id;

	@Column(name = "type_name")
	private String typeName;

	public QuestionTypeEntity() {
		super();
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
