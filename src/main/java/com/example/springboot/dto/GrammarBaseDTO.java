package com.example.springboot.dto;

public class GrammarBaseDTO {
	private Long id;
	private String lessonName;
	private String grammar;
	private String explanation;
	private String grammarMeaning;
	private String example;
	private String exampleMeaning;
	private String exampleImageLink;

	public GrammarBaseDTO() {
	}

	public GrammarBaseDTO(Long id, String lessonName, String grammar, String explanation, String grammarMeaning,
			String example, String exampleMeaning, String exampleImageLink) {
		this.id = id;
		this.lessonName = lessonName;
		this.grammar = grammar;
		this.explanation = explanation;
		this.grammarMeaning = grammarMeaning;
		this.example = example;
		this.exampleMeaning = exampleMeaning;
		this.exampleImageLink = exampleImageLink;
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

	public String getGrammar() {
		return grammar;
	}

	public void setGrammar(String grammar) {
		this.grammar = grammar;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getGrammarMeaning() {
		return grammarMeaning;
	}

	public void setGrammarMeaning(String grammarMeaning) {
		this.grammarMeaning = grammarMeaning;
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
