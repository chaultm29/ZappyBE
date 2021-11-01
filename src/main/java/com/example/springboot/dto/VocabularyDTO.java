package com.example.springboot.dto;

public class VocabularyDTO {
	private Long id;
	private String imageLink;
	private String vocabulary;
	private String meaning;
	private String example;
	private String exampleMeaning;
	private String exampleImageLink;
	private Long lesson_id;

	public VocabularyDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VocabularyDTO(Long id, String imageLink, String vocabulary, String meaning, String example,
			String exampleMeaning, String exampleImageLink, Long lesson_id) {
		super();
		this.id = id;
		this.imageLink = imageLink;
		this.vocabulary = vocabulary;
		this.meaning = meaning;
		this.example = example;
		this.exampleMeaning = exampleMeaning;
		this.exampleImageLink = exampleImageLink;
		this.lesson_id = lesson_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getVocabulary() {
		return vocabulary;
	}

	public void setVocabulary(String vocabulary) {
		this.vocabulary = vocabulary;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getExampleMeaning() {
		return exampleMeaning;
	}

	public void setExampleMeaning(String exampleMeaning) {
		this.exampleMeaning = exampleMeaning;
	}

	public String getExampleImageLink() {
		return exampleImageLink;
	}

	public void setExampleImageLink(String exampleImageLink) {
		this.exampleImageLink = exampleImageLink;
	}

	public Long getLesson_id() {
		return lesson_id;
	}

	public void setLesson_id(Long lesson_id) {
		this.lesson_id = lesson_id;
	}

}
