package com.example.springboot.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "lesson")
public class LessonEntity {
	@Id
	@Column(name = "lesson_id")
	private Long id;

	@Column(name = "lesson_name")
	private String lessonName;

	@Column(name = "description")
	private String description;
	
	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	private Set<QuestionEntity> questionEntities;

	public LessonEntity() {
		super();
	}

	public LessonEntity(Long id, String lessonName, String description) {
		super();
		this.id = id;
		this.lessonName = lessonName;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
