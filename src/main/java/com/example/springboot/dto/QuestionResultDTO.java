package com.example.springboot.dto;

import java.util.List;

public class QuestionResultDTO {
	private String username;
	private List<AnswerDTO> answerDTOs;

	public QuestionResultDTO() {
	}

	public QuestionResultDTO(String username, List<AnswerDTO> answerDTOs) {
		this.username = username;
		this.answerDTOs = answerDTOs;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<AnswerDTO> getAnswerDTOs() {
		return answerDTOs;
	}

	public void setAnswerDTOs(List<AnswerDTO> answerDTOs) {
		this.answerDTOs = answerDTOs;
	}
}
