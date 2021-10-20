package com.example.springbootdto;

public class LessonDTO {
	private Long id;

	public LessonDTO() {
		super();
	}

	public LessonDTO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
