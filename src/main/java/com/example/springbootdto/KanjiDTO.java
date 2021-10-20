package com.example.springbootdto;

import com.example.springboot.entities.LessonEntity;

public class KanjiDTO {
	private Long id;
	private String character;
	private String onyomi;
	private String kunyomi;
	private String chinese;
	private String vietnamese;
	private String description;
	private String imageLink;
	private String gifLink;
	private Long lesson_id;

	public KanjiDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KanjiDTO(Long id, String character, String onyomi, String kunyomi, String chinese, String vietnamese,
			String description, String imageLink, String gifLink, Long lesson_id) {
		super();
		this.id = id;
		this.character = character;
		this.onyomi = onyomi;
		this.kunyomi = kunyomi;
		this.chinese = chinese;
		this.vietnamese = vietnamese;
		this.description = description;
		this.imageLink = imageLink;
		this.gifLink = gifLink;
		this.lesson_id = lesson_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getOnyomi() {
		return onyomi;
	}

	public void setOnyomi(String onyomi) {
		this.onyomi = onyomi;
	}

	public String getKunyomi() {
		return kunyomi;
	}

	public void setKunyomi(String kunyomi) {
		this.kunyomi = kunyomi;
	}

	public String getChinese() {
		return chinese;
	}

	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

	public String getVietnamese() {
		return vietnamese;
	}

	public void setVietnamese(String vietnamese) {
		this.vietnamese = vietnamese;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getGifLink() {
		return gifLink;
	}

	public void setGifLink(String gifLink) {
		this.gifLink = gifLink;
	}

	public Long getLesson_id() {
		return lesson_id;
	}

	public void setLesson_id(Long lesson_id) {
		this.lesson_id = lesson_id;
	}

}
