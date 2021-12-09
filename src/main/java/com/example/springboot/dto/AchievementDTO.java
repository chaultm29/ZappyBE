package com.example.springboot.dto;

public class AchievementDTO {
	private Long id;

	private String name;

	private String desciption;

	private String condition;
	
	private String imageLink;

	public AchievementDTO() {
		// TODO Auto-generated constructor stub
	}

	public AchievementDTO(Long id, String name, String desciption, String condition) {
		super();
		this.id = id;
		this.name = name;
		this.desciption = desciption;
		this.condition = condition;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	
}
