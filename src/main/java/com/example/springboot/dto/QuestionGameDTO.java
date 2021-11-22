package com.example.springboot.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class QuestionGameDTO {

	private Long questionID;
	private String question;
	private ArrayList<AnswerDTO> answers;
	
	private String imageLink;
	
	public QuestionGameDTO() {
		
	}

	public QuestionGameDTO(Long questionID, String question, ArrayList<AnswerDTO> answers, String imageLink) {
		super();
		this.questionID = questionID;
		this.question = question;
		this.answers = answers;
		this.imageLink = imageLink;
	}

	public Long getQuestionID() {
		return questionID;
	}

	public void setQuestionID(Long questionID) {
		this.questionID = questionID;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public ArrayList<AnswerDTO> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<AnswerDTO> answers) {
		this.answers = answers;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

}
