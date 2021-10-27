package com.example.springbootdto;

import java.util.List;

public class SettingExamDTO {
	private List<Long> skillList;
	private List<Long> questionTypeList;
	private List<Long> idLessonList;
	private int numberOfQuestion;

	public SettingExamDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SettingExamDTO(List<Long> skillList, List<Long> questionTypeList, List<Long> idLessonList,
			int numberOfQuestion) {
		super();
		this.skillList = skillList;
		this.questionTypeList = questionTypeList;
		this.idLessonList = idLessonList;
		this.numberOfQuestion = numberOfQuestion;
	}

	public List<Long> getSkillList() {
		return skillList;
	}

	public void setSkillList(List<Long> skillList) {
		this.skillList = skillList;
	}

	public List<Long> getQuestionTypeList() {
		return questionTypeList;
	}

	public void setQuestionTypeList(List<Long> questionTypeList) {
		this.questionTypeList = questionTypeList;
	}

	public List<Long> getIdLessonList() {
		return idLessonList;
	}

	public void setIdLessonList(List<Long> idLessonList) {
		this.idLessonList = idLessonList;
	}

	public int getNumberOfQuestion() {
		return numberOfQuestion;
	}

	public void setNumberOfQuestion(int numberOfQuestion) {
		this.numberOfQuestion = numberOfQuestion;
	}

}
