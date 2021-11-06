package com.example.springboot.dto;

import java.util.ArrayList;
import java.util.List;

public class QuestionRequireDTO {

	private List<Long> lessonIds = new ArrayList<>();
	private List<Long> skillIds = new ArrayList<>();
	private List<Long> typeIds = new ArrayList<>();
	private int numberOfQuestion;


	public QuestionRequireDTO() {
	}

	public QuestionRequireDTO(List<Long> lessonIds, List<Long> skillIds, List<Long> typeIds, int numberOfQuestion) {
		this.lessonIds = lessonIds;
		this.skillIds = skillIds;
		this.typeIds = typeIds;
		this.numberOfQuestion = numberOfQuestion;
	}

	public List<Long> getLessonIds() {
		return lessonIds;
	}

	public void setLessonIds(List<Long> lessonIds) {
		this.lessonIds = lessonIds;
	}

	public List<Long> getSkillIds() {
		return skillIds;
	}

	public void setSkillIds(List<Long> skillIds) {
		this.skillIds = skillIds;
	}

	public List<Long> getTypeIds() {
		return typeIds;
	}

	public void setTypeIds(List<Long> typeIds) {
		this.typeIds = typeIds;
	}

	public int getNumberOfQuestion() {
		return numberOfQuestion;
	}

	public void setNumberOfQuestion(int numberOfQuestion) {
		this.numberOfQuestion = numberOfQuestion;
	}
}
