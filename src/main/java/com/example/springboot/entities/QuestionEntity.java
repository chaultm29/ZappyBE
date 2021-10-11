package com.example.springboot.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class QuestionEntity {
	@Id
	@Column(name = "question_id")
	private Long id;

	@Column(name = "image_link")
	private String image_link;

	@Column(name = "question")
	private String question;

	@Column(name = "level")
	private String level;
	
	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	private Set<AnswerEntity> answerEntities;
	
	@ManyToOne
	@JoinColumn(name = "type_id")	

	private QuestionTypeEntity questionTypeEntity;
	
	@ManyToMany(mappedBy = "questionEntities")
	private Set<RoomEntity> roomEntities;


	public QuestionEntity() {
	}

	public QuestionEntity(Long id, String image_link, String question, String level) {
		super();
		this.id = id;
		this.image_link = image_link;
		this.question = question;
		this.level = level;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage_link() {
		return image_link;
	}

	public void setImage_link(String image_link) {
		this.image_link = image_link;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
