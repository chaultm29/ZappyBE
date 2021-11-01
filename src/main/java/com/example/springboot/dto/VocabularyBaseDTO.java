package com.example.springboot.dto;

public class VocabularyBaseDTO {
	private String lessonName;
	private String vocabulary;
	private String meaning;
	private String imageLink;
	private String example;
	private String exampleMeaning;
	private String exampleImageLink;

	public VocabularyBaseDTO() {
	}

	public VocabularyBaseDTO(String lessonName, String vocabulary, String meaning, String imageLink, String example,
			String exampleMeaning, String exampleImageLink) {
		this.lessonName = lessonName;
		this.vocabulary = vocabulary;
		this.meaning = meaning;
		this.imageLink = imageLink;
		this.example = example;
		this.exampleMeaning = exampleMeaning;
		this.exampleImageLink = exampleImageLink;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
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

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
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
}
