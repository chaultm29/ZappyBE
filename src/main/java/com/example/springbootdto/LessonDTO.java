package com.example.springbootdto;

public class LessonDTO {
	private Long id;
	private String lessonName;

	public LessonDTO() {
		super();
	}

	public LessonDTO(Long id, String lessonName) {
		super();
		this.id = id;
		this.lessonName = lessonName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

}
