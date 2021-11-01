package com.example.springboot.dto;

public class QuestionRequireDTO {

	private Long lessonId;
	private Long skillId;
	private Long typeId;
	private int quantity;

	public QuestionRequireDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionRequireDTO(Long lessonId, Long skillId, Long typeId, int quantity) {
		super();
		this.lessonId = lessonId;
		this.skillId = skillId;
		this.typeId = typeId;
		this.quantity = quantity;
	}

	public Long getLessonId() {
		return lessonId;
	}

	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
