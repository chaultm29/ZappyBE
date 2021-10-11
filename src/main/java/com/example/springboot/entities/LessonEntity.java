package com.example.springboot.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "lessons")
public class LessonEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "lesson_name")
	private String lessonName;

	@Column(name = "description")
	private String description;
	
	@OneToMany(mappedBy = "lessonEntity", cascade = CascadeType.ALL)
	private Set<QuestionEntity> questionEntities;
	
	@OneToMany(mappedBy = "lessonEntity", cascade = CascadeType.ALL)
	private Set<GrammarEntity> grammarEntities;
	
	@OneToMany(mappedBy = "lessonEntity", cascade = CascadeType.ALL)
	private Set<VocabularyEntity> vocabularyEntities;
	
	@OneToMany(mappedBy = "lessonEntity", cascade = CascadeType.ALL)
	private Set<KanjiEntity> kanjiEntities;
	
	@ManyToMany(mappedBy = "lessonEntities")
	private Set<RoomEntity> roomEntities;
	

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
