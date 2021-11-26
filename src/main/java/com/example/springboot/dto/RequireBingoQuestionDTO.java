package com.example.springboot.dto;

import java.util.ArrayList;
import java.util.List;

public class RequireBingoQuestionDTO {
	private List<Integer> lessonIds = new ArrayList<>();
	private List<Integer> questionIds = new ArrayList<>();
	
	public RequireBingoQuestionDTO() {
	}

	public RequireBingoQuestionDTO(List<Integer> lessonIds, List<Integer> questionIds) {
		super();
		this.lessonIds = lessonIds;
		this.questionIds = questionIds;
	}

	public List<Integer> getLessonIds() {
		return lessonIds;
	}

	public void setLessonIds(List<Integer> lessonIds) {
		this.lessonIds = lessonIds;
	}

	public List<Integer> getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(List<Integer> questionIds) {
		this.questionIds = questionIds;
	}
	
}
