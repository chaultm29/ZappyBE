package com.example.springboot.dto;

public class AnswerDTO {
	private Long id;
	private String answer;

	public AnswerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AnswerDTO(Long id, String answer) {
		super();
		this.id = id;
		this.answer = answer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
