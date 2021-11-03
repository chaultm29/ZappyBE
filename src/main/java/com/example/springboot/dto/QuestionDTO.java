package com.example.springboot.dto;

import java.util.List;
import java.util.Set;

import com.example.springboot.entities.AnswerEntity;

public class QuestionDTO {
	private Long questionID;
	private String typeName;
	private String lessonName;
	private String skillName;
	private String question;
	private Set<AnswerEntity> answer;
	private String imgeLink;

	public QuestionDTO() {
	}

	public QuestionDTO(Long questionID, String typeName, String lessonName, String skillName, String question, Set<AnswerEntity> answer, String imgeLink) {
		this.questionID = questionID;
		this.typeName = typeName;
		this.lessonName = lessonName;
		this.skillName = skillName;
		this.question = question;
		this.answer = answer;
		this.imgeLink = imgeLink;
	}

	public Long getQuestionID() {
		return questionID;
	}

	public void setQuestionID(Long questionID) {
		this.questionID = questionID;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Set<AnswerEntity> getAnswer() {
		return answer;
	}

	public void setAnswer(Set<AnswerEntity> answer) {
		this.answer = answer;
	}

	public String getImgeLink() {
		return imgeLink;
	}

	public void setImgeLink(String imgeLink) {
		this.imgeLink = imgeLink;
	}
}
