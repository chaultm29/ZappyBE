package com.example.springboot.dto;

import java.util.List;

import com.example.springboot.entities.AnswerEntity;

public class QuestionDTO {
	private Long questionID;
	private String lesson;
	private String type;
	private String question;
	private String imgLink;
	private String skill;
	private List<AnswerEntity> answerEntities;

	public QuestionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionDTO(Long questionID, String lesson, String type, String question, String imgLink, String skill,
			List<AnswerEntity> answerEntities) {
		super();
		this.questionID = questionID;
		this.lesson = lesson;
		this.type = type;
		this.question = question;
		this.imgLink = imgLink;
		this.skill = skill;
		this.answerEntities = answerEntities;
	}

	public Long getQuestionID() {
		return questionID;
	}

	public void setQuestionID(Long questionID) {
		this.questionID = questionID;
	}

	public String getLesson() {
		return lesson;
	}

	public void setLesson(String lesson) {
		this.lesson = lesson;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public List<AnswerEntity> getAnswerEntities() {
		return answerEntities;
	}

	public void setAnswerEntities(List<AnswerEntity> answerEntities) {
		this.answerEntities = answerEntities;
	}

}
