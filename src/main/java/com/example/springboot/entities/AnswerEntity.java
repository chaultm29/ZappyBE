package com.example.springboot.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Answer")
public class AnswerEntity {
	@Id
	@Column(name = "answer_id")
	private Long id;

	@Column(name = "correct")
	private boolean correct;

	@Column(name = "image_link")
	private String image_link;

	@Column(name = "answer")
	private String answer;

	public AnswerEntity() {
		super();
	}

	public AnswerEntity(Long id, boolean correct, String image_link, String answer) {
		super();
		this.id = id;
		this.correct = correct;
		this.image_link = image_link;
		this.answer = answer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public String getImage_link() {
		return image_link;
	}

	public void setImage_link(String image_link) {
		this.image_link = image_link;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
