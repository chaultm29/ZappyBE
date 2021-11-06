package com.example.springboot.dto;

import java.util.ArrayList;
import java.util.List;

public class QuestionResultDetailDTO {
	private Integer numberOfCorrect;
	private Integer score;
	private List<AnswerDTO> correct = new ArrayList<>();

	public QuestionResultDetailDTO() {
	}

	public QuestionResultDetailDTO(Integer numberOfCorrect, Integer score, List<AnswerDTO> correct) {
		this.numberOfCorrect = numberOfCorrect;
		this.score = score;
		this.correct = correct;
	}

	public Integer getNumberOfCorrect() {
		return numberOfCorrect;
	}

	public void setNumberOfCorrect(Integer numberOfCorrect) {
		this.numberOfCorrect = numberOfCorrect;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public List<AnswerDTO> getCorrect() {
		return correct;
	}

	public void setCorrect(List<AnswerDTO> correct) {
		this.correct = correct;
	}
}
